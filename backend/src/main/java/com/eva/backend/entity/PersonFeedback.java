package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Author：
 * Date：2023/1/1018:06
 * Desc:
 */
@Data
@Entity
@Table(name = "person_feedback")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class PersonFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略，数据库自增长
    @Column(name = "id")
    int id;

    @Column(name = "user_id")
    int userId;

    /**
     * 热感觉
     */
    @Column(name = "thermal_sen")
    float thermalSens;

    /**
     * 湿度感觉
     */
    @Column(name = "humid_sen")
    float humidSens;

    /**
     * 热可接受性
     */
    @Column(name = "thermal_accept")
    int thermalAccept;

    /**
     * 热舒适性
     */
    @Column(name = "thermal_comfort")
    int thermalComfort;

    /**
     * 风速可接受性
     */
    @Column(name = "av_accept")
    int airVelAccept;

    /**
     * 实时热偏好
     */
    @Column(name = "thermal_pref")
    int thermalPref;

    /**
     * 心情
     */
    @Column(name = "mood")
    int mood;

    /**
     * 填表时间
     */
    @Column(name = "gmt_create")
    Date gmtCreate;

}

