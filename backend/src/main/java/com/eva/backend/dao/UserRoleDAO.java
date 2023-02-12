package com.eva.backend.dao;

import com.eva.backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Author：
 * Date：2023/1/1021:29
 * Desc:
 */
public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByUserId(int userId);
    void deleteAllByUserId(int userId);
}
