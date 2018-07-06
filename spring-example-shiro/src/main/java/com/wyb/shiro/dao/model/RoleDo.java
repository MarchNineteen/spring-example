package com.wyb.shiro.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Kunzite
 */
@Data
@Table(name = "shiro.role")
public class RoleDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

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

    /**
     * 初始化常量
     *
     * @return
     */
    public RoleDo init() {
        this.setIsDelete(0);
        this.setCreateTime(new Date());
        this.setUpdateTime(new Date());
        return this;
    }
}