package com.gc.vblog.config;

import com.gc.vblog.web.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    /**
    * 视图控制器
    */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //指定请求对应的视图  
        //registry.addViewController("/").setViewName("/index");
    }
    /**
    * 允许跨域
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //所有url都允许跨域
            	//允许的请求方式
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")  
                .allowCredentials(true) //允许请求携带凭证
                //允许跨域的源，如果设置为 * 表示所有源
                .allowedOrigins("*");
    }

    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册日志记录拦截器，拦截所有请求
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }


}