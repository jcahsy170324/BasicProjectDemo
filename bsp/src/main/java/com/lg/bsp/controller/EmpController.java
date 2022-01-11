package com.lg.bsp.controller;

import com.lg.bsp.common.VResponse;
import com.lg.bsp.model.Emp;
import com.lg.bsp.service.EmpService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

/**
 * @ClassName EmpController
 * @Description 员工模块控制层
 * @Author jincheng
 * @Date 2022/1/11 13:45
 * @Version 1.0
 **/
@Api("员工模块")
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;


    @GetMapping("/findByCondition")
    public VResponse<Object> findEmpByCondition(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(required = false) Integer empno,
                                                @RequestParam(required = false) String ename,@RequestParam(required = false) String job,
                                                @RequestParam(required = false)Integer mgr, @RequestParam(required = false)Date hiredate,
                                                @RequestParam(required = false)Double sal,@RequestParam(required = false) Double comm){
        List<Emp> empByCondition = empService.findEmpByCondition(pageNum, pageSize, empno, ename, job, mgr, hiredate, sal, comm);
        return VResponse.success(empByCondition);
    }

}
