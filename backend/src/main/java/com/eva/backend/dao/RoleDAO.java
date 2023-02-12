package com.eva.backend.dao;

import com.eva.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author：
 * Date：2023/1/1021:09
 * Desc:
 */
public interface RoleDAO extends JpaRepository<Role,Integer> {
    Role findById(int id);
    Role findByRoleName(String name);
}
