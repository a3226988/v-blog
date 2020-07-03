package com.gc.vblog.dao;

import com.gc.vblog.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Create by gc on 2020/7/3
 * 铁甲依然在
 */
public interface UserDao extends Mapper<User> {

    @Select("select id,username from user where id = #{id}")
    public User getUserInfo(int id);
}
