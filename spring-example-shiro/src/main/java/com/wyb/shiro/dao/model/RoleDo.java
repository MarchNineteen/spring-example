package com.wyb.shiro.dao.model;

import com.wyb.shiro.dao.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "shiro.role")
@Data
public class RoleDo extends BaseEntity implements Serializable {
    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

}