package com.eva.backend.service;

import com.eva.backend.dao.RightDAO;
import com.eva.backend.dao.RoleDAO;
import com.eva.backend.dao.RoleRightDAO;
import com.eva.backend.entity.Right;
import com.eva.backend.entity.Role;
import com.eva.backend.entity.RoleRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author：
 * Date：2023/1/1810:30
 * Desc:
 */
@Service
public class RightService {
    @Autowired
    RoleService roleService;
    @Autowired
    RoleRightService roleRightService;
    @Autowired
    RightDAO rightDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    RoleRightDAO roleRightDAO;

    public List<Right> list() {
        return rightDAO.findAll();
    }

    /**
     * Determine whether client requires permission when requests
     * a certain API.
     * @param requestAPI API requested by client
     * @return true when requestAPI is found in the DB
     */
    public boolean needFilter(String requestAPI) {
        List<Right> rights = rightDAO.findAll();

        for (Right right: rights) {
            // match prefix
            if (requestAPI.startsWith(right.getRightUrl())) {
                return true;
            }
            System.out.println(right.getRightName());
        }
        return false;
    }

    public List<Right> listRightByRoleId(int roleId) {
        List<Integer> rightIds = roleRightDAO.findAllByRoleId(roleId).stream().map(RoleRight::getRightId).collect(Collectors.toList());
        return rightDAO.findAllById(rightIds);
    }


    /**
     * get all rights url by userName
     * @param username
     * @return
     */
    public Set<String> listRightURLsByUser(String username) {
        List<Integer> roleIds = roleService.listRolesByUser(username).stream().map(Role::getId).collect(Collectors.toList());

        List<Integer> rightIds = roleRightDAO.findAllByRoleIdIn(roleIds)
                .stream().map(RoleRight::getRightId).collect(Collectors.toList());

        List<Right> rights = rightDAO.findAllById(rightIds);

        Set<String> URLs = rights.stream().map(Right::getRightUrl).collect(Collectors.toSet());

        return URLs;
    }

}
