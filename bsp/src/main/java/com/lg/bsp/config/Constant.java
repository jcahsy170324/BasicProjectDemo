package com.lg.bsp.config;

import com.lg.bsp.common.PropertiesUtil;

import java.util.Properties;

/**
 * @ClassName Constant
 * @Description 常量类
 * @Author jincheng
 * @Date 2022/1/10 10:53
 * @Version 1.0
 **/
public class Constant {

    static{
        try {
            Properties properties = PropertiesUtil.readPropertiesFile("application.yml");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
