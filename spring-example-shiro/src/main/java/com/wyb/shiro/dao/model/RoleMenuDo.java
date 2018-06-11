package com.wyb.shiro.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "shiro.role_menu")
public class RoleMenuDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private Integer menuId;

    private static final long serialVersionUID = 1L;
}