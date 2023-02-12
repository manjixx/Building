package com.eva.backend.dao;

import com.eva.backend.entity.Menu;
import com.eva.backend.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author：
 * Date：2023/1/1318:40
 * Desc:
 */
public interface RoleMenuDAO extends JpaRepository<RoleMenu,Integer> {
    List<RoleMenu> findAllByRoleId(int roleId);
    List<RoleMenu> findAllByRoleIdIn(List<Integer> roleIds);
    @Transactional
    void deleteAllByRoleId(int roleId);

}
