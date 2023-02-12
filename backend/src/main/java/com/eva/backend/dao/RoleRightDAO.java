package com.eva.backend.dao;

import com.eva.backend.entity.RoleRight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author：
 * Date：2023/1/1021:07
 * Desc:
 */
public interface RoleRightDAO extends JpaRepository<RoleRight,Integer> {
    List<RoleRight> findAllByRoleId(int roleId);
    List<RoleRight> findAllByRoleIdIn(List<Integer> roleIds);
    RoleRight findByRoleId(int roleId);
    void deleteAllByRoleId(int roleId);
}
