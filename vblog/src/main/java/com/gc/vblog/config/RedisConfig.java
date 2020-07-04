package com.gc.vblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Create by gc on 2020/7/4
 * 铁甲依然在
 */
@Configuration
public class RedisConfig {

    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    public ValueOperations valueOperations(){
        return redisTemplate.opsForValue();
    }
    @Bean
    public SetOperations listOperations(){
        return redisTemplate.opsForSet();
    }
}
