package com.gc.vblog.util;

/**
 * Create by gc on 2020/7/3
 * 铁甲依然在
 */

public class MyUtil {
    /**
     * 随机生成6位数字
     */
    public static String randomNum(){
        int newNum = (int)((Math.random()*9+1)*100000);
        return String.valueOf(newNum);
    }
}
