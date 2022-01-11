package com.lg.bsp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description 简单用户类
 * @Author jincheng
 * @Date 2022/1/6 15:25
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String gender;
}
