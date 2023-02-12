package com.eva.backend.controller;

import com.eva.backend.entity.Role;
import com.eva.backend.result.Result;
import com.eva.backend.result.ResultFactory;
import com.eva.backend.service.RightService;
import com.eva.backend.service.RoleMenuService;
import com.eva.backend.service.RoleRightService;
import com.eva.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Author：
 * Date：2023/1/1817:33
 * Desc:
 */
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RightService rightService;
    @Autowired
    RoleRightService roleRightService;
    @Autowired
    RoleMenuService roleMenuService;

    @GetMapping("/api/admin/role")
    public Result listRoles(){
        return ResultFactory.buildSuccessResult(roleService.listWithRightAndMenus());
    }

    @GetMapping("/api/admin/role/perm")
    public Result listPerms() {
        return ResultFactory.buildSuccessResult(rightService.list());
    }

    @PostMapping("/api/admin/role")
    public Result addRole(@RequestBody Role requestRole) {
        System.out.println(requestRole);
        boolean isExist = roleService.roleExist(requestRole);
        if(isExist){
            return ResultFactory.buildFailResult("角色已经存在");
        }
        roleService.addRole(requestRole);
        System.out.println("添加角色成功");
        return ResultFactory.buildSuccessResult("添加角色成功,请通过'编辑'配置权限与菜单");
    }

    @PutMapping("/api/admin/role/status")
    public Result updateRoleStatus(@RequestBody Role requestRole){
        Role role = roleService.updateRoleStatus(requestRole);
        String message = "用户" + role.getRoleDesc() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/api/admin/role")
    public Result editRole(@RequestBody Role requestRole) {
        roleService.addOrUpdate(requestRole);
        roleRightService.saveRightChanges(requestRole.getId(), requestRole.getRights());
        String message = "修改角色信息成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/api/admin/role/menu")
    public Result updateRoleMenu(@RequestParam("rid") int roleId, @RequestBody Map<String, List<Integer>> menusIds) {
        roleMenuService.updateRoleMenu(roleId, menusIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }


    @DeleteMapping("/api/admin/role")
    public Result deleteRole(@RequestParam("rid") int roleId) throws Exception {
        if(roleService.deleteRole(roleId) == false){
            return ResultFactory.buildFailResult("删除角色信息失败");
        }
        return ResultFactory.buildSuccessResult("删除角色信息成功");
    }
}
