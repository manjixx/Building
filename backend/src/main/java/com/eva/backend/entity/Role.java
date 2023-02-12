package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Author：
 * Date：2023/1/1020:03
 * Desc:
 */
@Data
@Entity
@Table(name = "role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     *  role  name
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * role description
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * role state
     * 0: prohibit
     * 1: permit
     */
    @Column(name = "role_state")
    private boolean roleState;

    /**
     * create time
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * modified time
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * Transient property for storing permissions owned by current role.
     */
    @Transient
    private List<Right> rights;

    /**
     * Transient property for storing menus owned by current role.
     */
    @Transient
    private List<Menu> menus;
}
