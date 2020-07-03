package com.gc.vblog.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Create by gc on 2020/6/23
 * 通用mapper插件时实体类的属性不能使用基本数据类型，默认不映射基本类型的属性
 * 铁甲依然在
 */
@Data
public class User {
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate;
    private String email;
}
