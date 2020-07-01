package com.gc.vblog.commons;

import lombok.Data;

/**
 * 自定义的返回结果封装类
 * 铁甲依然在
 */
@Data
public class Result {
    /**
     * 执行结果状态码
     */
    private int stateCode;
    /**
     * 响应的消息
     */
    private String msg;
    /**
     * 响应的数据
     */
    private Object data;

    public static Result newInstance(int stateCode,Object data,String msg){
        Result result = new Result();
        result.setStateCode(stateCode);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result success(Object data,String msg){
        return newInstance(200,data,msg);
    }
    public static Result success(Object data){
        return newInstance(200,data,"操作成功");
    }
    public static Result success(){
       return newInstance(200,null,"操作成功");
    }

    public static Result fail(Object data,String msg){
        return newInstance(500,data,msg);
    }
    public static Result fail(String msg){
        return newInstance(500,null,msg);
    }
    public static Result fail(){
        return newInstance(500,null,"操作失败");
    }
}
