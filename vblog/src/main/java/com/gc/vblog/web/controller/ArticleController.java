package com.gc.vblog.web.controller;

import com.gc.vblog.commons.Result;
import com.gc.vblog.entity.Article;
import com.gc.vblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

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
    public Result detail(@PathVariable("id") Integer id, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        System.out.println("ip:"+ip);
        Map<String,Object> map =  articleService.articleDetail(id,ip);
        if(map.get("status").equals(200)){
            return Result.success(map);
        }
        return Result.fail("文章加载失败！");
    }


    /**
     * 点赞
     */
    @GetMapping("like")
    public Result giveLike(Integer articleId,HttpSession session,Integer userId){
        /*User user = (User) session.getAttribute("loginuser");
        article.setUserId(user.getId());*/
        long num = articleService.giveLike(articleId,userId);
        return Result.success(num);
    }

    /**
     * 取消点赞
     */
    @GetMapping("canclelike")
    public Result cancleLike(Integer articleId,Integer userId){
        long num = articleService.cancelLike(articleId,userId);
        return Result.success(num);
    }
}
