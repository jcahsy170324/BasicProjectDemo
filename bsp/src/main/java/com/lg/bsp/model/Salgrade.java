package com.lg.bsp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Salgrade
 * @Description 工资
 * @Author jincheng
 * @Date 2022/1/11 13:41
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salgrade implements Serializable{
    private Integer grade;
    private Integer losal;
    private Integer hisal;
}
