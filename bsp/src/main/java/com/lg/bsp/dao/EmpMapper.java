package com.lg.bsp.dao;

import com.lg.bsp.model.Dept;
import com.lg.bsp.model.Emp;

import java.util.HashMap;
import java.util.List;

public interface EmpMapper {
    List<Emp> findEmpByCondition(HashMap<String, Object> map);

    Emp findEmpByEmpno(Integer empno);

    Integer updateEmpByCondition(Emp emp);

    List<Emp> findEmpInEmpnos(List<Integer> empnos);

    Emp findEmpJoinDeptByEmpno(Integer empno);

    Dept findDeptJoinEmpsByDeptno(Integer deptno);

    Integer addEmp(Emp emp);

    Integer deleteEmp(Integer empno);
}
