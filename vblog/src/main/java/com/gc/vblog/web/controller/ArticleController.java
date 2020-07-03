package com.gc.vblog.web.controller;

import com.gc.vblog.commons.Result;
import com.gc.vblog.entity.Article;
import com.gc.vblog.entity.User;
import com.gc.vblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 文章控制器类
 * Create by gc on 2020/7/1
 * 铁甲依然在
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result publish(Article article, HttpSession session){
        article.setCreated(new Date());
        article.setModified(new Date());
        /*User user = (User) session.getAttribute("loginuser");
        article.setUserId(user.getId());*/
        article.setUserId(1);
        if(articleService.saveAritcle(article))
            return Result.success();
        return Result.fail("发布失败");
    }

    @GetMapping("{id}")
    public Result detail(@PathVariable("id") Integer id){
        Article article =  articleService.articleDetail(id);
        if(article!=null)
            return Result.success(article);
        return Result.fail("文章加载失败！");
    }
}
