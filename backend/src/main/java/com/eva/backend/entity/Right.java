package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Author：
 * Date：2023/1/1020:03
 * Desc:
 */
@Data
@Entity
@Table(name = "permission")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Right {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * right name
     */
    @Column(name = "permission_name")
    private String rightName;

    /**
     * right description in chinese
     */
    @Column(name = "permission_desc")
    private String rightDesc;

    /**
     * right state
     * 0: prohibit
     * 1: permit
     */
    @Column(name = "permission_state")
    private boolean rightState;

    /**
     * right url
     */
    @Column(name = "permission_url")
    private String rightUrl;

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
}
