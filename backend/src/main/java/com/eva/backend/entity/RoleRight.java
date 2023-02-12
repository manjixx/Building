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
@Table(name = "role_permission")
@JsonIgnoreProperties({"handler","hibernateInitializer"})
public class RoleRight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    /**
     * role id
     */
    @Column(name = "role_id")
    int roleId;

    /**
     * right id
     */
    @Column(name = "permission_id")
    int rightId;

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
