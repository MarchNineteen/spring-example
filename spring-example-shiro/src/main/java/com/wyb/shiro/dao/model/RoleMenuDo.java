package com.wyb.shiro.dao.model;

import com.wyb.shiro.dao.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "shiro.role_menu")
@Data
public class RoleMenuDo extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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