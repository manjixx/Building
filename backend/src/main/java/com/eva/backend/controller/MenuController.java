package com.eva.backend.controller;

import com.eva.backend.dao.MenuDAO;
import com.eva.backend.entity.Menu;
import com.eva.backend.result.Result;
import com.eva.backend.result.ResultFactory;
import com.eva.backend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author：
 * Date：2023/1/1320:25
 * Desc:
 */
@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/api/menu")
    public List<Menu> menu() {
        return menuService.getMenusByCurrentUser();
    }

    @GetMapping("/api/admin/role/menu")
    public Result listAllMenus(){
        return ResultFactory.buildSuccessResult(menuService.getMenusByRoleId(1));
    }
}
