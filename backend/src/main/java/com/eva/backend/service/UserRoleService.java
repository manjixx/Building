package com.eva.backend.service;

import com.eva.backend.dao.UserRoleDAO;
import com.eva.backend.entity.Role;
import com.eva.backend.entity.User;
import com.eva.backend.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author：
 * Date：2023/1/1319:57
 * Desc:
 */
@Service
public class UserRoleService {
    @Autowired
    UserRoleDAO userRoleDAO;
    public List<UserRole> listAllByUserId(int id) {
        return userRoleDAO.findAllByUserId(id);
    }

    @Transactional
    public void saveRoleChanges(int uid, List<Role> roles) {
        List<UserRole> userRolesInDB = userRoleDAO.findAllByUserId(uid);
        userRoleDAO.deleteAllByUserId(uid);
        Date gmt = new Date(System.currentTimeMillis());
        List<UserRole> userRoles = new ArrayList<>();
        roles.forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(uid);
            userRole.setRoleId(role.getId());
            if(userRolesInDB.size() != 0){
                userRole.setGmtCreate(userRolesInDB.get(0).getGmtCreate());
            }else{
                userRole.setGmtCreate(gmt);
            }
            userRole.setGmtModified(gmt);
            userRoles.add(userRole);
        });
        userRoleDAO.saveAll(userRoles);
    }

    @Transactional
    public void deleteAllByUserId(int userId) {
        userRoleDAO.deleteAllByUserId(userId);
    }

    public List<UserRole> findByUserId(int userId) {
        List<UserRole> users = userRoleDAO.findAllByUserId(userId);
        return users;
    }
}
