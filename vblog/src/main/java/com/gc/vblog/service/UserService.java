package com.gc.vblog.service;

import com.gc.vblog.commons.RedisKeyConstant;
import com.gc.vblog.dao.UserDao;
import com.gc.vblog.entity.User;
import com.gc.vblog.util.EmailUtil;
import com.gc.vblog.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务层
 * Create by gc on 2020/7/3
 * 铁甲依然在
 */
@Service
@Transactional
public class UserService {


    @Autowired
    EmailUtil emailUtil;
    /**
     * 注入带泛型的RedisTemplate<Serializable,String>对象必须使用Resource
     */
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserDao userDao;

    /**
     * 用户注册
     * @param user 用户信息
     * @param request 请求对象
     * @return 注册成功还是失败
     */
    public boolean register(User user, HttpServletRequest request) throws Exception {
        //初始化用户信息
        user.setStatus(0);
        user.setCreatedate(new Date());
        //redis可以存储很多种类型的数据，不同的数据类型使用的操作类不一样，ValueOperations用于操作简单的类型，例如常用的String类型
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        if(userDao.insert(user)>0){
            //生成6位数验证码
            String verifyCode = MyUtil.randomNum();
            //动态生成激活链接地址
            String activeUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"user/"+user.getId()+"/active?vertifyCode="+verifyCode;
            //生成邮件内容
            String msg = "<html><body><h1>邮箱验证</h1><p>请点击以下链接激活账户：<a href='"+activeUrl+"'>"+activeUrl+"</a></p><p>如链接点击无法跳转，请复制链接直接通过浏览器访问</p></body></html>";
            //发送验证链接
            emailUtil.sendVertifyMsg("邮箱验证",msg,user.getEmail());
            //将验证码存入redis,并设置5分钟到期,或者选择存入application中，但是无法设置过期时间
            valueOperations.set(RedisKeyConstant.VERIFYCODE +user.getId(),verifyCode,5, TimeUnit.MINUTES);
            return true;
        }
        return false;
    }

    /**
     * 激活用户
     * @param user 用户对象，主要传递用户id
     * @param verifyCode 激活码
     */
    public boolean activateUser(User user,String verifyCode){
        String key = RedisKeyConstant.VERIFYCODE+user.getId();
        //判断改用户对应的激活码是否存在
        if(redisTemplate.hasKey(key)){
            ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
            //取出改用户对应的激活码
            String vcode = valueOperations.get(key);
            //验证激活码是否正确
            if(vcode.equals(verifyCode)){
                user.setStatus(1);
                if(userDao.updateByPrimaryKeySelective(user)>0){
                    //激活成功后删除缓存里的数据
                    redisTemplate.delete(key);
                    return true;
                }
            }
        }
        return false;
    }


}
