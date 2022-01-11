package com.lg.bsp.dao;

import com.lg.bsp.model.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User selectOne(Integer id);

    int insert(User user);

    int deleteByPrimaryKey(Integer id);

    int updateOne(User user);
}
