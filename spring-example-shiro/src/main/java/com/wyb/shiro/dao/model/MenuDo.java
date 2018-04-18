package com.wyb.shiro.dao.model;

import com.wyb.shiro.dao.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "shiro.menu")
@Data
public class MenuDo extends BaseEntity implements Serializable {
    /**
     * 父级菜单id
     */
    @Column(name = "p_id")
    private Integer pId;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

}