package com.lg.bsp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
    private Integer deptno;
    private String dname;
    private String loc;

    /**
     * @Description //组合一个emp的List作为属性
     **/
    private List<Emp> empList;
}
