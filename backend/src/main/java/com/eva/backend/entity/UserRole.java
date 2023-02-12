package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Author：
 * Date：2023/1/1020:05
 * Desc:
 */
@Data
@Entity
@Table(name = "user_role")
@JsonIgnoreProperties({"handler", "hibernateInitializer"})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    /**
     * User Id
     */
    @Column(name = "user_id")
    int userId;

    /**
     * Role Id
     */
    @Column(name = "role_id")
    int roleId;

    /**
     * create time
     */
    @Column(name = "gmt_create")
    Date gmtCreate;

    /**
     * modified time
     */
    @Column(name = "gmt_modified")
    Date gmtModified;
}
