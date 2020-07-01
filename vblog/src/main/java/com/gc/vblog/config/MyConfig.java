package com.gc.vblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
}