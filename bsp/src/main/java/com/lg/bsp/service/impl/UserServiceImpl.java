package com.lg.bsp.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.bsp.common.MyPageInfo;
import com.lg.bsp.dao.UserMapper;
import com.lg.bsp.model.User;
import com.lg.bsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户模块服务层的实现
 * @Author jincheng
 * @Date 2022/1/6 15:39
 * @Version 1.0
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public MyPageInfo<User> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        MyPageInfo<User> myPageInfo = new MyPageInfo<>(pageNum,pageSize,pageInfo.getTotal(),pageInfo.getPages(),users);
        return myPageInfo;
    }

    @Override
    public User findOne(Integer id) {
        return userMapper.selectOne(id);
    }

    @Override
    public int insertOne(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteOne(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateOne(User user) {
        return userMapper.updateOne(user);
    }


}
