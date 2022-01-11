package com.lg.bsp.service.impl;

import com.github.pagehelper.PageHelper;
import com.lg.bsp.dao.EmpMapper;
import com.lg.bsp.model.Emp;
import com.lg.bsp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName EmpServiceImpl
 * @Description TODO
 * @Author jincheng
 * @Date 2022/1/11 13:46
 * @Version 1.0
 **/
@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Emp> findEmpByCondition(Integer pageNum, Integer pageSize, Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm) {
        HashMap<String,Object> map = new HashMap<>(10);
        map.put("empno",empno);
        map.put("ename",ename);
        map.put("job",job);
        map.put("mgr",mgr);
        map.put("hiredate",hiredate);
        map.put("sal",sal);
        map.put("comm",comm);
        PageHelper.startPage(pageNum,pageSize);
        empMapper.findEmpByCondition(map);
        return null;
    }
}
