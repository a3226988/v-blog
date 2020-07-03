package com.gc.vblog.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("客户端发送了【"+request.getRequestURL()+"】请求，传递的请求参数为：【"+request.getParameterMap()+"】,映射的controlelr方法为：【"+handler+"】");
        return true;
    }
}