package com.gc.vblog.web.controller;

import com.gc.vblog.commons.Result;
import com.gc.vblog.entity.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章控制器类
 * Create by gc on 2020/7/1
 * 铁甲依然在
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    String mdstr = "";

    @PostMapping
    public Result publish(Article article){
        mdstr = article.getContent();
        System.out.println(mdstr);
        return Result.success();
    }

    @GetMapping
    public Result detail(){
        return Result.success(mdstr);
    }
}
