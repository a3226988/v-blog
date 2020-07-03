package com.gc.vblog;

import com.gc.vblog.entity.Article;
import com.gc.vblog.entity.User;
import com.gc.vblog.service.ArticleService;
import com.gc.vblog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;

@SpringBootTest
class VblogApplicationTests {

    @Autowired
    ArticleService service;

    @Autowired
    UserService userService;

    @Resource
    RedisTemplate<String,String> redisTemplate;

    @Test
    void contextLoads() {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name","李四");
        System.out.println(valueOperations.get("name"));
    }


    public void addArticle(){
        Article article = new Article();
        article.setTitle("Java泛型的应用");
        article.setAllowComment(false);
        article.setCreated(new Date());
        article.setModified(new Date());
        article.setTags("Java,JavaSE,");
        article.setContent("# 泛型!<T>  ```java public class Ob<T>{} ``` ");
        article.setUserId(10);
        service.saveAritcle(article);
    }
}
