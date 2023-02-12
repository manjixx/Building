package com.eva.backend.controller;

import com.eva.backend.entity.Device;
import com.eva.backend.entity.User;
import com.eva.backend.result.Result;
import com.eva.backend.result.ResultFactory;
import com.eva.backend.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Author：
 * Date：2023/1/1816:46
 * Desc:
 */
@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;


    @GetMapping("/api/admin/device")
    public Result listDevice(){
        return ResultFactory.buildSuccessResult(deviceService.listAll());
    }

    @PutMapping("/api/admin/device/status")
    public Result updateSensorStatus(@RequestBody @Valid Device requestDevice) {
        deviceService.updateDeviceStatus(requestDevice);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }


    @PutMapping("/api/admin/device")
    public Result editSensor(@RequestBody @Valid Device requestDevice) {
        deviceService.editSensor(requestDevice);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    @DeleteMapping("/api/admin/device")
    public Result deleteDevice(@RequestParam("sid") long sid) throws Exception{
        if(deviceService.deleteDevice(sid) == false){
            return ResultFactory.buildFailResult("删除设备失败");
        }
        return ResultFactory.buildSuccessResult("删除设备成功");
    }

    @PostMapping(value = "api/admin/device")
    @ResponseBody
    public Result register(@RequestBody Device device) {
        int status = deviceService.addDevice(device);
        switch (status){
            case 0:
                return ResultFactory.buildSuccessResult("添加设备成功");
            case 1:
                return ResultFactory.buildFailResult("设备已经存在，请检查设备编号是否输入有误");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
}
