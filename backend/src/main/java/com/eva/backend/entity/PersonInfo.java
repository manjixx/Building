package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Author：
 * Date：2023/1/1018:04
 * Desc:
 */
@Data
@Entity
@Table(name = "person_info")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class PersonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    /**
     * 用户id
     * */
    @Column(name = "user_id")
    int userId;

    /**
     * 用户性别
     */
    @Column(name = "gender")
    String gender;

    /**
     * 用户年龄
     */
    @Column(name = "age")
    int age;

    /**
     * 家乡
     */

    @Column(name = "region")
    String region;

    /**
     * 主观热偏好
     */
    @Column(name = "pref")
    int pref;

    /**
     * 主观热敏感度
     */
    @Column(name = "sen")
    int sen;

    /**
     * 对实验环境的主观评价
     */
    @Column(name = "eval")
    int eval;

    /**
     * 室内温度的重要性
     */
    @Column(name = "imp_indoor_tmp")
    int impInTmp;

    /**
     * 室内湿度的重要性
     */
    @Column(name = "imp_indoor_rh")
    int impInRh;

    /**
     * 室内空气质量的重要性
     */
    @Column(name = "imp_indoor_aq")
    int impInAQ;

    /**
     * 室内空气流速的重要性
     */
    @Column(name = "imp_indoor_av")
    int impInAV;

    /**
     * 天气的重要性
     */
    @Column(name = "imp_weather")
    int impWW;

    /**
     * 室外温度的重要性
     */
    @Column(name = "imp_outdoor_tmp")
    int impOutTmp;

    /**
     * 室外湿度的重要性
     */
    @Column(name = "imp_outdoor_rh")
    int impOutRh;

    /**
     * 心情的重要性
     */
    @Column(name = "imp_mood")
    int impMood;

    /**
     * 心情会提高对环境的容忍度
     */
    @Column(name = "tolerance")
    int tolerance;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    Date gmtModified;
}

