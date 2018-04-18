package com.wyb.shiro.dao.model;

import com.wyb.shiro.dao.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "shiro.user_role")
@Data
public class UserRoleDo extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    private static final long serialVersionUID = 1L;

}