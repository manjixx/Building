package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Author：
 * Date：2023/2/815:32
 * Desc:
 */
@Data
@Entity
@Table(name = "device")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "device_type")
    private int deviceType;

    @Column(name = "device_status")
    private boolean enable;

    @Column(name = "device_health")
    private int deviceHealth;

    @Column(name = "location")
    private String location;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;
}
