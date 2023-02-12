package com.eva.backend.dao;

import com.eva.backend.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author：
 * Date：2023/1/1318:39
 * Desc:
 */
public interface MenuDAO extends JpaRepository<Menu,Integer> {
    Menu findById(int id);
    List<Menu> findAllByParentId(int parentId);
}
