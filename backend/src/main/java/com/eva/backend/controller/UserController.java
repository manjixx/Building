package com.eva.backend.controller;

import com.eva.backend.entity.User;
import com.eva.backend.result.Result;
import com.eva.backend.result.ResultFactory;
import com.eva.backend.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Author：
 * Date：2023/1/1816:46
 * Desc:
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/api/admin/user")
    public Result listUser(){
        return ResultFactory.buildSuccessResult(userService.list());
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    @DeleteMapping("/api/admin/user")
    public Result deleteRole(@RequestParam("uid") int userId) throws Exception {
        boolean isAdmin = userService.isAdmin(userId);
        if(isAdmin){
            return ResultFactory.buildFailResult("不可删除管理员账户");
        }
        if(userService.deleteRole(userId) == false){
            return ResultFactory.buildFailResult("删除角色信息失败");
        }
        return ResultFactory.buildSuccessResult("删除角色信息成功");
    }
}
