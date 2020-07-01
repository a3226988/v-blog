package com.gc.vblog.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Create by gc on 2020/6/23
 * 铁甲依然在
 */

@Data
public class User {
    private Integer id;
    private String username;
    private String name;
    private String password;
    private int status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate;
}
