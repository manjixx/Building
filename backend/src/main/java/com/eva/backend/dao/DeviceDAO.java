package com.eva.backend.dao;

import com.eva.backend.entity.Device;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author：
 * Date：2023/2/815:43
 * Desc:
 */
public interface DeviceDAO extends JpaRepository<Device,Integer> {
    void deleteById(long id);
    Device findById(long id);
    Device findDeviceByDeviceId(String deviceId);

}
