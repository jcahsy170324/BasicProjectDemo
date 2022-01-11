package com.lg.bsp.service;

import com.lg.bsp.common.MyPageInfo;
import com.lg.bsp.model.User;



public interface UserService {
    MyPageInfo<User> findAll(Integer pageNum, Integer pageSize);

    User findOne(Integer id);

    int insertOne(User user);

    int deleteOne(Integer id);

    int updateOne(User user);
}
