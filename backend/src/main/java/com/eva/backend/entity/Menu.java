package com.eva.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Author：
 * Date：2023/1/1318:27
 * Desc:
 */
@Data
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "name_zh")
    private String nameZh;

    @Column(name = "icon_cls")
    private String iconCls;

    @Column(name = "component")
    private String component;

    @Column(name = "parent_id")
    int parentId;

    @Column(name = "gmt_create")
    Date gmtCreate;

    @Column(name = "gmt_modified")
    Date gmtModified;

    @Transient
    List<Menu> children;
}
