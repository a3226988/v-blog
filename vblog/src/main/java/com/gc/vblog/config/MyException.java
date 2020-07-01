package com.gc.vblog.config;

import com.gc.vblog.commons.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import java.io.IOException;

/**
 * 全局异常处理
 * 铁甲依然在
 */
@Slf4j
@RestControllerAdvice
public class MyException {
    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result handler(BindException e) throws IOException {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        log.error("数据验证异常，"+bindingResult.getTarget().getClass().getSimpleName()+"类："+objectError.getDefaultMessage());
        return Result.fail(objectError.getDefaultMessage());
    }

    /**
     * 全局异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Result CatchRtEx(HandlerMethod handlerMethod, Exception e) throws IOException {
        log.error("【"+handlerMethod.getMethod().getName().toString()+"】出现异常,异常信息为【"+e.getMessage()+"】");
        return Result.fail(e.getMessage());
    }
}