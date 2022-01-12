package com.lg.bsp.controller;

import com.lg.bsp.common.MyPageInfo;
import com.lg.bsp.common.VResponse;
import com.lg.bsp.model.Dept;
import com.lg.bsp.model.Emp;
import com.lg.bsp.service.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("分页查询员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "分页数量", dataType = "Integer"),
            @ApiImplicitParam(name = "empno", value = "员工编号", dataType = "Integer"),
            @ApiImplicitParam(name = "ename", value = "员工姓名", dataType = "String"),
            @ApiImplicitParam(name = "job", value = "岗位", dataType = "String"),
            @ApiImplicitParam(name = "mgr", value = "上级编号", dataType = "Integer"),
            @ApiImplicitParam(name = "hiredate", value = "入职日期", dataType = "Date"),
            @ApiImplicitParam(name = "sal", value = "薪资", dataType = "Double"),
            @ApiImplicitParam(name = "comm", value = "红利", dataType = "Double"),
            @ApiImplicitParam(name = "deptno", value = "部门编号", dataType = "Integer")
    }
    )
    @GetMapping("/findByCondition")
    public VResponse<Object> findEmpByCondition(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(required = false) Integer empno,
                                                @RequestParam(required = false) String ename,@RequestParam(required = false) String job,
                                                @RequestParam(required = false)Integer mgr, @RequestParam(required = false)Date hiredate,
                                                @RequestParam(required = false)Double sal,@RequestParam(required = false) Double comm,
                                                @RequestParam(required = false) Integer deptno){
        MyPageInfo<Emp> empByCondition = empService.findEmpByCondition(pageNum, pageSize, empno, ename, job, mgr, hiredate, sal, comm,deptno);
        return VResponse.success(empByCondition);
    }

    @ApiOperation("修改员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "emp", value = "员工信息", dataType = "Emp")
    })
    @PutMapping("/update")
    public VResponse<Object> updateEmpByCondition(@RequestBody Emp emp){
        Integer result = empService.updateEmpByCondition(emp);
        return result > 0 ? VResponse.success("update success"):VResponse.error(0,"update fail");
    }

    @ApiOperation("根据员工编号集查找员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empnos", value = "员工编号集", dataType = "List<Integer>")
    })
    @GetMapping("/findEmpInEmpnos")
    public VResponse<Object> findEmpInEmpnos(@RequestParam List<Integer> empnos){
        List<Emp> empInEmpnos = empService.findEmpInEmpnos(empnos);
        return VResponse.success(empInEmpnos);
    }

    @ApiOperation("根据员工编号查询员工信息和部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empno", value = "员工编号", dataType = "Integer")
    })
    @GetMapping("/findEmpJoinDeptByEmpno")
    public VResponse<Object> findEmpJoinDeptByEmpno(@RequestParam Integer empno){
        Emp empJoinDeptByEmpno = empService.findEmpJoinDeptByEmpno(empno);
        return VResponse.success(empJoinDeptByEmpno);
    }

    @ApiOperation("根据部门编号查询部门信息和该部门所有员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "部门编号", dataType = "Integer")
    })
    @GetMapping("/findDeptJoinEmpsByDeptno")
    public VResponse<Object> findDeptJoinEmpsByDeptno(@RequestParam Integer deptno){
        Dept deptJoinEmpsByDeptno = empService.findDeptJoinEmpsByDeptno(deptno);
        return VResponse.success(deptJoinEmpsByDeptno);
    }

    @ApiOperation("新增员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "emp", value = "员工信息", dataType = "Emp")
    })
    @PostMapping("/addEmp")
    public VResponse<Object> addEmpDept(@RequestBody Emp emp){
        Integer result = empService.addEmp(emp);
        return result > 0 ? VResponse.success("add success") : VResponse.error(0,"add fail");
    }

    @ApiOperation("删除员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empno", value = "员工编号", dataType = "Integer")
    })
    @DeleteMapping("/deleteEmp")
    public VResponse<Object> deleteEmp(@RequestParam Integer empno){
        Integer result = empService.deleteEmp(empno);
        return result > 0 ? VResponse.success("delete success") : VResponse.error(0,"delete fail");
    }

}
