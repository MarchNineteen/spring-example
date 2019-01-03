package com.wyb.shiro.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "shiro.menu")
public class MenuDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p_id")
    private Integer pid;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "is_show")
    private Integer isShow;

    /**
     * 描述
     */
    private String description;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}