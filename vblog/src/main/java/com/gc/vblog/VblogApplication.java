package com.gc.vblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gc.vblog.dao")
public class VblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(VblogApplication.class, args);
    }
}
