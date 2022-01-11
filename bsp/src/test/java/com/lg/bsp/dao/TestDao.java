package com.lg.bsp.dao;

import com.lg.bsp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName TestDao
 * @Description Dao测试类
 * @Author jincheng
 * @Date 2022/1/7 11:51
 * @Version 1.0
 **/
@SpringBootTest
public class TestDao {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        User user = userMapper.selectOne(2);
        System.out.println(user);
    }

}
