package com.eva.backend.service;

import com.eva.backend.dao.PersonInfoDAO;
import com.eva.backend.entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：
 * Date：2023/1/1021:41
 * Desc:
 */
@Service
public class FormService {
    @Autowired
    PersonInfoDAO personInfoDAO;
    public void save(PersonInfo personInfo) {
        personInfoDAO.save(personInfo);
    }
}
