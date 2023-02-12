package com.eva.backend.dao;

import com.eva.backend.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author：
 * Date：2023/1/1020:39
 * Desc:
 */
public interface PersonInfoDAO extends JpaRepository<PersonInfo,Integer> {
    PersonInfo findByUserId(int userId);
}
