package com.eva.backend.service;

import com.eva.backend.dao.DeviceDAO;
import com.eva.backend.dto.UserDTO;
import com.eva.backend.entity.Device;
import com.eva.backend.entity.Role;
import com.eva.backend.entity.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author：
 * Date：2023/2/815:40
 * Desc:
 */
@Service
public class DeviceService {

    @Autowired
    DeviceDAO deviceDAO;


    public List<Device> listAll() {
        List<Device> devices = deviceDAO.findAll();
        return devices;
    }

    public void updateDeviceStatus(Device device) {
        Device deviceInDB = deviceDAO.findById(device.getId());
        deviceInDB.setEnable(device.isEnable());
        deviceInDB.setGmtModified(new Date(System.currentTimeMillis()));
        deviceDAO.save(deviceInDB);
    }

    public void editSensor(Device device) {
        Device deviceInDB = deviceDAO.findById(device.getId());
        deviceInDB.setDeviceName(device.getDeviceName());
        deviceInDB.setDeviceType(device.getDeviceType());
        deviceInDB.setDeviceHealth(device.getDeviceHealth());
        deviceInDB.setLocation(device.getLocation());
        deviceInDB.setGmtModified(new Date(System.currentTimeMillis()));
        deviceDAO.save(deviceInDB);
    }

    @Transactional 
    public boolean deleteDevice(long sensorId) throws Exception {
        try {
            deviceDAO.deleteById(sensorId);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean isExist(String deviceId){
        Device device = deviceDAO.findDeviceByDeviceId(deviceId);
        return null != device;
    }

    public int addDevice(Device device) {
        String deviceId = device.getDeviceId();
        String deviceName = device.getDeviceName();
        int deviceType = device.getDeviceType();
        int deviceHealth = device.getDeviceHealth();
        String location = device.getLocation();
        Date gmt = new Date(System.currentTimeMillis());

        boolean exist = isExist(deviceId);

        if (exist) {
            return 1;
        }
        device.setDeviceId(deviceId);
        device.setDeviceName(deviceName);
        device.setDeviceType(deviceType);
        device.isEnable();
        System.out.println(device.isEnable());
        device.setDeviceHealth(deviceHealth);
        device.setLocation(location);
        device.setGmtCreate(gmt);
        device.setGmtModified(gmt);

        deviceDAO.save(device);

        return 0;
    }
}
