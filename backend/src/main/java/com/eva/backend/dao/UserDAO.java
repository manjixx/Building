package com.eva.backend.dao;

import com.eva.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author：
 * Date：2022/12/623:18
 * Desc:
 */
public interface UserDAO extends JpaRepository<User,Integer> {
    // 由于使用了jpa，无需手动构建SQL语句，按照规范提供方法名即可实现对数据库的增删改查
    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);
}
