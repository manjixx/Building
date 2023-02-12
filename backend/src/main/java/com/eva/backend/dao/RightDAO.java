package com.eva.backend.dao;

import com.eva.backend.entity.Right;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author：
 * Date：2023/1/1020:42
 * Desc:
 */
public interface RightDAO extends JpaRepository<Right, Integer> {
    Right findById(int id);
}
