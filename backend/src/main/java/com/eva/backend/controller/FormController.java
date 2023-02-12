package com.eva.backend.controller;

import com.eva.backend.dto.PersonInfoDTO;
import com.eva.backend.entity.PersonInfo;
import com.eva.backend.result.Result;
import com.eva.backend.result.ResultFactory;
import com.eva.backend.service.FormService;
import com.eva.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Author：
 * Date：2023/1/1021:40
 * Desc:
 */
@RestController
public class FormController {
    @Autowired
    FormService formService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/user/info")
    @ResponseBody
    public Result uploadInfo(@RequestBody PersonInfoDTO personInfoDTO){
        String username = personInfoDTO.getUsername();

        int userId = userService.getUserIdByName(username);
        if(userId == -1){
            return ResultFactory.buildFailResult("用户不存在，请检查用户名填写是否正确");
        } else {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setUserId(userId);
            personInfo.setGender(personInfoDTO.getGender());
            personInfo.setAge(personInfoDTO.getAge());
            personInfo.setRegion(personInfoDTO.getRegion());
            personInfo.setPref(personInfoDTO.getPref());
            personInfo.setEval(personInfoDTO.getEval());
            personInfo.setImpInTmp(personInfoDTO.getImpInTmp());
            personInfo.setImpInRh(personInfoDTO.getImpInRh());
            personInfo.setImpInAQ(personInfoDTO.getImpInAQ());
            personInfo.setImpInAV(personInfoDTO.getImpInAV());
            personInfo.setImpWW(personInfoDTO.getImpWW());
            personInfo.setImpOutTmp(personInfoDTO.getImpOutTmp());
            personInfo.setImpOutRh(personInfoDTO.getImpOutRh());
            personInfo.setImpWW(personInfoDTO.getImpWW());
            personInfo.setImpMood(personInfoDTO.getImpMood());
            personInfo.setTolerance(personInfoDTO.getTolerance());
            Date date = new Date(System.currentTimeMillis());
            personInfo.setGmtCreate(date);
            personInfo.setGmtModified(date);
            formService.save(personInfo);
            return ResultFactory.uploadSuccessResult("上传个人信息问卷成功");
        }
    }
}
