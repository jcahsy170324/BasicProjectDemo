package com.lg.bsp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.bsp.common.MyPageInfo;
import com.lg.bsp.dao.EmpMapper;
import com.lg.bsp.model.Dept;
import com.lg.bsp.model.Emp;
import com.lg.bsp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
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
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public MyPageInfo<Emp> findEmpByCondition(Integer pageNum, Integer pageSize, Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        HashMap<String, Object> map = new HashMap<>(10);
        map.put("empno", empno);
        map.put("ename", ename);
        map.put("job", job);
        map.put("mgr", mgr);
        map.put("hiredate", hiredate);
        map.put("sal", sal);
        map.put("comm", comm);
        map.put("deptno", deptno);
        PageHelper.startPage(pageNum, pageSize);
        List<Emp> empByCondition = empMapper.findEmpByCondition(map);
        PageInfo<Emp> pageInfo = new PageInfo<>(empByCondition);
        MyPageInfo<Emp> myPageInfo = new MyPageInfo<>(pageNum,pageSize,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
        return myPageInfo;
    }

    @Override
    public Integer updateEmpByCondition(Emp emp) {
        Integer empno = emp.getEmpno();
        Integer result = -1;
        if (null == empno){
            return -1;
        }else{
            Emp empByEmpno = empMapper.findEmpByEmpno(empno);
            if (null == empByEmpno){
                return -1;
            }else {
                result = empMapper.updateEmpByCondition(emp);
            }
        }
        return result;
    }

    @Override
    public List<Emp> findEmpInEmpnos(List<Integer> empnos) {
        List<Emp> emps= new ArrayList<>();
        String key = null;
        for (Integer id : empnos) {
            key = "emp:" + id;
            //??????redis???????????????
            if (redisTemplate.hasKey(key)){
                System.out.println("????????????");
                redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Emp>(Emp.class));
                Emp emp = (Emp) redisTemplate.opsForValue().get(key);
                if (null != emp){
                    emps.add(emp);
                }
            }
        }
        if (null != emps){
            return emps;
        }
        List<Emp> empInEmpnos = empMapper.findEmpInEmpnos(empnos);
        for (Emp empInEmpno : empInEmpnos) {
            key = "emp:" + empInEmpno.getEmpno();
            redisTemplate.opsForValue().set(key,empInEmpno);
        }
        return empInEmpnos;
    }

    @Override
    public Emp findEmpJoinDeptByEmpno(Integer empno) {
        return empMapper.findEmpJoinDeptByEmpno(empno);
    }

    @Override
    public Dept findDeptJoinEmpsByDeptno(Integer deptno) {
        return empMapper.findDeptJoinEmpsByDeptno(deptno);
    }

    @Override
    public Integer addEmp(Emp emp) {
        return empMapper.addEmp(emp);
    }

    @Override
    public Integer deleteEmp(Integer empno) {
        return empMapper.deleteEmp(empno);
    }
}
