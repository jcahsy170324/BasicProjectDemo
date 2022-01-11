package com.lg.bsp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Dept
 * @Description 部门
 * @Author jincheng
 * @Date 2022/1/11 13:37
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {
    private Integer deptNo;
    private String dname;
    private String loc;
}
