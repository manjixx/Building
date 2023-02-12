package com.eva.backend.service;

import com.eva.backend.dao.RoleDAO;
import com.eva.backend.dao.RoleMenuDAO;
import com.eva.backend.dao.RoleRightDAO;
import com.eva.backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author：
 * Date：2023/1/1810:35
 * Desc:
 */
@Service
public class RoleService {
    @Autowired
    RoleDAO roleDAO;

    @Autowired
    RoleMenuDAO roleMenuDAO;

    @Autowired
    RoleRightDAO roleRightDAO;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Lazy
    @Autowired
    RightService rightService;

    @Autowired
    RoleRightService roleRightService;
    @Autowired
    MenuService menuService;



    public List<Role> listWithRightAndMenus() {
        List<Role> roles = roleDAO.findAll();
        List<Right> rights;
        List<Menu> menus;
        for (Role role : roles) {
            rights = rightService.listRightByRoleId(role.getId());
            menus = menuService.getMenusByRoleId(role.getId());
            role.setRights(rights);
            role.setMenus(menus);
        }
        return roles;
    }

    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    public void addRole(@RequestBody Role role) {
        role.setRoleState(true);
        Date gmt = new Date(System.currentTimeMillis());
        role.setGmtCreate(gmt);
        role.setGmtModified(gmt);
        role.setRights(null);
        role.setMenus(null);
        roleDAO.save(role);
    }

    public void addOrUpdate(Role requestRole) {
        Role role = requestRole;
        Role roleInDB = roleDAO.findByRoleName(requestRole.getRoleName());
        Date date = new Date(System.currentTimeMillis());
        if (roleInDB == null){
            role.setGmtCreate(date);
        }
        role.setGmtCreate(roleInDB.getGmtCreate());
        role.setGmtModified(date);
        roleDAO.save(role);
    }

    public List<Role> listRolesByUser(String username) {
        int uid = userService.findByUsername(username).getId();
        List<Integer> roleIds = userRoleService.listAllByUserId(uid)
                .stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return roleDAO.findAllById(roleIds);
    }

    public Role updateRoleStatus(Role role) {
        Role roleInDB = roleDAO.findById(role.getId());
        roleInDB.setRoleState(role.isRoleState());
        roleInDB.setGmtModified(new Date(System.currentTimeMillis()));
        roleDAO.save(roleInDB);
        return roleInDB;
    }


    public boolean roleExist(Role requestRole) {
        Role roleInDB = roleDAO.findByRoleName(requestRole.getRoleName());
        if(roleInDB != null){
            return true;
        }
        return false;
    }

    public boolean deleteRole(int roleId) throws Exception {
        try {
            roleDAO.deleteById(roleId);
            roleRightDAO.deleteAllByRoleId(roleId);
            System.out.println(roleMenuDAO.findAllByRoleId(roleId));
            roleMenuDAO.deleteAllByRoleId(roleId);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
