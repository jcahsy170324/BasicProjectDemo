package com.lg.bsp.dao;

import com.lg.bsp.model.Emp;

import java.util.HashMap;
import java.util.List;

public interface EmpMapper {
    List<Emp> findEmpByCondition(HashMap<String, Object> map);
}
