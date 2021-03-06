package com.lg.bsp.service;

import com.lg.bsp.common.MyPageInfo;
import com.lg.bsp.model.Dept;
import com.lg.bsp.model.Emp;

import java.sql.Date;
import java.util.List;

public interface EmpService {
    MyPageInfo<Emp> findEmpByCondition(Integer pageNum, Integer pageSize, Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno);

    Integer updateEmpByCondition(Emp emp);

    List<Emp> findEmpInEmpnos(List<Integer> empnos);

    Emp findEmpJoinDeptByEmpno(Integer empno);

    Dept findDeptJoinEmpsByDeptno(Integer deptno);

    Integer addEmp(Emp emp);

    Integer deleteEmp(Integer empno);
}
