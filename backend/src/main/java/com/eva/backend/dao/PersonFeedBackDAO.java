package com.eva.backend.dao;

import com.eva.backend.entity.PersonFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author：
 * Date：2023/1/1020:41
 * Desc:
 */
public interface PersonFeedBackDAO extends JpaRepository<PersonFeedback,Integer> {
    PersonFeedback findByUserId(int userId);
}
