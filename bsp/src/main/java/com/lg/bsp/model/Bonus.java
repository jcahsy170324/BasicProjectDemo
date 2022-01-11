package com.lg.bsp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Bonus
 * @Description 红利
 * @Author jincheng
 * @Date 2022/1/11 13:40
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bonus implements Serializable {
    private String ename;
    private String job;
    private Double sal;
    private Double comm;
}
