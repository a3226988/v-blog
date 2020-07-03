package com.gc.vblog.web.controller;

import com.gc.vblog.commons.BlogConstant;
import com.gc.vblog.commons.Result;
import com.gc.vblog.entity.User;
import com.gc.vblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by gc on 2020/7/3
 * 铁甲依然在
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    BlogConstant blogConstant;

    @PostMapping("login")
    @ResponseBody
    public Result login(){
        return Result.fail("登陆失败！");
    }

    @PostMapping
    @ResponseBody
    public Result register(User user, HttpServletRequest request){
        try {
            if(userService.register(user,request)){
                return Result.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("注册失败！");
    }

    /**
     * 激活用户，该方法不能使用异步响应，因为客户端需要通过url访问后跳转到项目前端的页面
     * @param user 用户对象，主要传递用户id，只不过用对象封装
     * @param vertifyCode  验证码
     * @return 如果激活成功那么可以跳转到前端项目的登陆页面，失败则跳转到激活失败页面
     */
    @GetMapping("{id}/active")
    public String activeUser(User user,String vertifyCode){
        if(userService.activateUser(user,vertifyCode)){
            return "redirect:"+blogConstant.getActive_success_url();
        }
        return "redirect:"+blogConstant.getActive_fail_url();
    }

}
