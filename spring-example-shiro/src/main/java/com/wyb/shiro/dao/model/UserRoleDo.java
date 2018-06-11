package com.wyb.shiro.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "shiro.user_role")
public class UserRoleDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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