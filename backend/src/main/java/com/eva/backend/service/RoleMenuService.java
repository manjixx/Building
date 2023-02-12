package com.eva.backend.service;

import com.eva.backend.dao.RoleMenuDAO;
import com.eva.backend.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author：
 * Date：2023/1/1320:02
 * Desc:
 */
@Service
public class RoleMenuService {
    @Autowired
    RoleMenuDAO roleMenuDAO;

    public List<RoleMenu> findAllByRoleId(int roleId) {
        return roleMenuDAO.findAllByRoleId(roleId);
    }

    public List<RoleMenu> findAllByRoleId(List<Integer> roleIds) {
        return roleMenuDAO.findAllByRoleIdIn(roleIds);
    }

    public void save(RoleMenu roleMenu) {
        roleMenuDAO.save(roleMenu);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(int roleId, Map<String, List<Integer>> menusIds) {
        List<RoleMenu> roleMenuInDB = roleMenuDAO.findAllByRoleId(roleId);
        roleMenuDAO.deleteAllByRoleId(roleId);
        List<RoleMenu> roleMenus = new ArrayList<>();
        Date gmtCreate = new Date();
        Date gmtModified = new Date(System.currentTimeMillis());
        if(!roleMenuInDB.isEmpty()){
            gmtCreate = roleMenuInDB.get(0).getGmtCreate();
        }else {
            gmtCreate = gmtModified;
        }
        for (Integer menuId : menusIds.get("menusIds")) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenu.setGmtCreate(gmtCreate);
            roleMenu.setGmtModified(gmtModified);
            roleMenus.add(roleMenu);
        }
        roleMenuDAO.saveAll(roleMenus);
    }
}
