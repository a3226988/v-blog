package com.gc.vblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.util.Date;

/**
 * Create by gc on 2020/6/23
 * 通用mapper插件时实体类的属性不能使用基本数据类型，默认不映射基本类型的属性
 * 铁甲依然在
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date createdate;
    private String email;
}
