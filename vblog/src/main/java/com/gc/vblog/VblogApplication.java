package com.gc.vblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.gc.vblog.dao")
public class VblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(VblogApplication.class, args);
    }
}
