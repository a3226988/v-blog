package com.gc.vblog.service;

import com.gc.vblog.commons.RedisKeyConstant;
import com.gc.vblog.dao.AritcleDao;
import com.gc.vblog.dao.UserDao;
import com.gc.vblog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 文章业务类
 * Create by gc on 2020/6/30
 * 铁甲依然在
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    private AritcleDao aritcleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    SetOperations<String,String> setOperations;

    @Autowired
    ValueOperations<String,String> valueOperations;
    /**
     * 查询测试
     * @return
     */
    public List<Article> queryAll(){
        //开启分页，查询第一页，每页显示10条
        //PageHelper.startPage(1,10);
        return aritcleDao.selectAll();
    }

    /**
     * 保存文章
     * @param article
     * @return
     */
    public boolean saveAritcle(Article article){
        if(aritcleDao.insertSelective(article)>0){
            return true;
        }
        return false;
    }

    /**
     * 浏览文章详情
     * @param id 文章id
     * @param ip 访问者ip
     * @return 文章详情、点赞数、浏览数、作者等信息，由于要返回多个数据，所以使用map来存储
     */
    public Map<String,Object> articleDetail(int id, String ip){
        Map<String,Object> map = new HashMap<>();
        //根据文章id查询文件详情
        Article article = aritcleDao.selectByPrimaryKey(id);
        if(article==null) {
            //如果文章不存在，返回错误信息
            map.put("status",500);
            return map;
        }
        //生成文章浏览记录的key
        String key = RedisKeyConstant.BROWSERECORD+ip+":"+id;
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        //判断redis中是否存在当前ip访问该文章的记录
        if(!redisTemplate.hasKey(key)){
            //如果不存在，那么新增浏览记录,并保存24小时
            valueOperations.set(key,"1",24, TimeUnit.HOURS);
            //同时浏览量+1，increment方法用于将指定key的value递增1，如果key不存在会自动创建然后+1
            valueOperations.increment(RedisKeyConstant.PAGEVIEW+id);
        }
        //获取文章的浏览量
        String pv = valueOperations.get(RedisKeyConstant.PAGEVIEW+id);
        //获取文章的点赞数，由于类型是set集合，需要SetOperations来操作
        SetOperations setOperations = redisTemplate.opsForSet();
        //根据点赞记录的长度获取点赞数
        long likenum = setOperations.size(RedisKeyConstant.LIKERECORD+id);
        //将所有数据封装到map集合
        map.put("article",article);
        map.put("author",userDao.getUserInfo(article.getUserId()));
        map.put("pv",pv);
        map.put("likenum",likenum);
        map.put("status",200);
        return map;
    }

    /**
     * 查询用户的文章
     * @param id
     * @return
     */
    public List<Article> queryArticleByUserid(int id){

        return null;
    }


    /**
     * 点赞
     * @param articleId 文章ID
     * @param userId  用户ID
     * @return 点赞数
     */
    public long giveLike(int articleId,int userId){
        String key = RedisKeyConstant.LIKERECORD+articleId;
        setOperations.add(key,String.valueOf(userId));
        return setOperations.size(key);
    }

    /**
     * 取消点赞
     * @param articleId 文章ID
     * @param userId  用户ID
     * @return 点赞数
     */
    public long cancelLike(int articleId,int userId){
        String key = RedisKeyConstant.LIKERECORD+articleId;
        setOperations.remove(key,String.valueOf(userId));
        return setOperations.size(key);
    }
}
