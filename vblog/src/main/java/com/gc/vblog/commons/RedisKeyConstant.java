package com.gc.vblog.commons;

/**
 * redis数据库存储的key的前缀
 * 用于拼接不同的变量生成唯一的key
 * Create by gc on 2020/7/3
 * 铁甲依然在
 */
public class RedisKeyConstant {
    /**
     * 验证码
     */
    public static final String VERIFYCODE="verify_code:";
    /**
     * 浏览记录
     */
    public static final String BROWSERECORD="browse_record:";
    /**
     * 浏览量
     */
    public static final String PAGEVIEW="page_view:";
    /**
     * 点赞记录
     */
    public static final String LIKERECORD="like_record:";
}
