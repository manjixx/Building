package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Author：
 * Date：2023/1/1318:34
 * Desc:
 */
@Data
@Entity
@Table(name = "role_menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class RoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "role_id")
    int roleId;

    @Column(name = "menu_id")
    int menuId;

    @Column(name = "gmt_create")
    Date gmtCreate;

    @Column(name = "gmt_modified")
    Date gmtModified;
}
