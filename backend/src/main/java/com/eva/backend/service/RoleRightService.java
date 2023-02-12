package com.eva.backend.service;

import com.eva.backend.dao.RoleRightDAO;
import com.eva.backend.entity.Right;
import com.eva.backend.entity.Role;
import com.eva.backend.entity.RoleRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author：
 * Date：2023/1/1810:38
 * Desc:
 */
@Service
public class RoleRightService {
    @Autowired
    RoleRightDAO roleRightDAO;
//    public List<RoleRight> findAllByRoleId(int roleId) {
//        return roleRightDAO.findAllByRoleId(roleId);
//    }

    @Transactional
    public void saveRightChanges(int roleId, List<Right> rights) {
        List<RoleRight> roleRightInDB = roleRightDAO.findAllByRoleId(roleId);
        roleRightDAO.deleteAllByRoleId(roleId);
        List<RoleRight> roleRights = new ArrayList<>();
        Date gmt = new Date(System.currentTimeMillis());
        rights.forEach(right -> {
            RoleRight roleRight = new RoleRight();
            roleRight.setRoleId(roleId);;
            roleRight.setRightId(right.getId());
            roleRight.setGmtModified(gmt);
            if(roleRightInDB.size() != 0){
                roleRight.setGmtCreate(roleRightInDB.get(0).getGmtCreate());
            }else{
                roleRight.setGmtCreate(gmt);
            }
            roleRights.add(roleRight);
        });
        roleRightDAO.saveAll(roleRights);
    }

    public boolean deleteAllByRoleId(int roleId) throws Exception {
        try {
            roleRightDAO.deleteAllByRoleId(roleId);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
