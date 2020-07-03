package com.gc.vblog.commons;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目常量类
 * Create by gc on 2020/7/3
 * 铁甲依然在
 */
@Component
@Data
@ConfigurationProperties("blog")
public class BlogConstant {
    String active_success_url;
    String active_fail_url;
}
