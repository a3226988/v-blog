package com.gc.vblog.service;

import com.gc.vblog.dao.AritcleDao;
import com.gc.vblog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    /**
     * 查询测试
     * @return
     */
    public List<Article> queryAll(){
        //开启分页，查询第一页，每页显示10条
        //PageHelper.startPage(1,10);
        return aritcleDao.selectAll();
    }

    public List<Article> queryArticleByUserid(int id){
        return null;
    }
}
