package com.lg.bsp.controller;

import com.lg.bsp.common.CookieUtil;
import com.lg.bsp.common.MyPageInfo;
import com.lg.bsp.common.VResponse;
import com.lg.bsp.model.User;
import com.lg.bsp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName UserController
 * @Description 用户模块控制层
 * @Author jincheng
 * @Date 2022/1/6 15:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
@Api("用户模块")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "分页数量", dataType = "Integer")
    }
    )
    @GetMapping("/findAll/{pageNum}/{pageSize}")
    public VResponse<Object> findAll(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        MyPageInfo<User> all = userService.findAll(pageNum, pageSize);
        return VResponse.success("OK",all);
    }

    @ApiOperation("根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer")
    }
    )
    @GetMapping("/findOne/{id}")
    public VResponse<Object> findOne(@PathVariable("id") Integer id){
        User one = userService.findOne(id);
        return VResponse.success("OK",one);
    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", dataType = "User")
    })
    @PostMapping("/insert")
    public VResponse<Object> insertOne(@RequestBody User user){
        int res = userService.insertOne(user);
        return res == 1? VResponse.success("add success"):VResponse.error(0,"add fail");
    }

    @ApiOperation("删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer")
    })
    @DeleteMapping("/deleteOne")
    public VResponse<Object> deleteOne(@RequestParam Integer id){
        int res = userService.deleteOne(id);
        return res == 1 ? VResponse.success("delete success"):VResponse.error(0,"delete fail");
    }

    @ApiOperation("更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", dataType = "User")
    })
    @PostMapping("/updateOne")
    public VResponse<Object> updateOne(@RequestBody User user){
        int res = userService.updateOne(user);
        return res == 1? VResponse.success("update success"):VResponse.error(0,"update fail");
    }


}
