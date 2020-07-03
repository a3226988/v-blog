package com.gc.vblog.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 文章实体类
 */
@Data
public class Article {
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;          // 文章id
    private String title;       // 文章标题
    private String content;     // 文章内容
    private Date created;       // 发布时间
    private Date modified;      // 修改时间
    private String tags;          // 文章标签
    private Boolean allowComment; //是否允许评论
    private String thumbnail;     // 文章缩略图
    private Integer userId;
}