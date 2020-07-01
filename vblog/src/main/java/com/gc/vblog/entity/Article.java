package com.gc.vblog.entity;

import lombok.Data;

import java.util.Date;

/**
 * 文章实体类
 */
@Data
public class Article {
    private Integer id;          // 文章id
    private String title;       // 文章标题
    private String content;     // 文章内容
    private Date created;       // 发布时间
    private Date modified;      // 修改时间
    private String tags;          // 文章标签
    private Boolean allowComment; //是否允许评论，默认为true
    private String thumbnail;     // 文章缩略图
}