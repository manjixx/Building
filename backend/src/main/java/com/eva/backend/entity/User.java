package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * Author：
 * Date：2022/12/620:38
 * Desc:
 */
@Data
@Entity // 实体类
@Table(name = "user")   // 表名
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    /**
     * user name
     */
    @NotEmpty(message = "用户名不能为空")

    String username;

    /**
     * password
     */
    String password;

    /**
     * salt
     */
    String salt;

    /**
     * phone
     */

    /**
     * email
     */

    /**
     * Real name.
     */
    @Column(name = "name")
    private String name;

    /**
     * User status.
     */
    @Column(name = "status")
    private boolean enable;

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

    /**
     * Transient property for storing role owned by current user.
     */
    @Transient
    private List<Role> roles;

}
