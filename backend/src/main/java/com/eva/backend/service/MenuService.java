package com.eva.backend.service;

import com.eva.backend.dao.MenuDAO;
import com.eva.backend.entity.Menu;
import com.eva.backend.entity.RoleMenu;
import com.eva.backend.entity.User;
import com.eva.backend.entity.UserRole;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author：
 * Date：2023/1/1319:55
 * Desc:
 */
@Service
public class MenuService {
    @Autowired
    MenuDAO menuDAO;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleMenuService roleMenuService;

    public List<Menu> getAllByParentId(int parentId) {
        return menuDAO.findAllByParentId(parentId);
    }

    public List<Menu> getMenusByCurrentUser() {
        // Get current user from DB.
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);

        // Get roles' ids of current user.
        List<Integer> roleIds = userRoleService.listAllByUserId(user.getId())
                .stream().map(UserRole::getRoleId).collect(Collectors.toList());

        // Get roles' ids of current user.
        List<Integer> menuIds = roleMenuService.findAllByRoleId(roleIds)
                .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Menu> menus = menuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // 处理菜单项的结构
        handleMenus(menus);
        return menus;
    }

    public List<Menu> getMenusByRoleId(int roleId) {
        List<Integer> menuIds = roleMenuService.findAllByRoleId(roleId)
                .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Menu> menus = menuDAO.findAllById(menuIds);
        handleMenus(menus);
        return menus;
    }


    /**
     * Adjust the Structure of the menu.
     *
     * @param menus Menu items list without structure
     */
    public void handleMenus(List <Menu> menus) {
        menus.forEach(m -> {
            List<Menu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }
}
