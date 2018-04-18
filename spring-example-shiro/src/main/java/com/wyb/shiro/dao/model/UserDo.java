package com.wyb.shiro.dao.model;

import com.wyb.shiro.dao.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "shiro.user")
@Data
public class UserDo extends BaseEntity implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 角色列表
     */
    private List<RoleDo> roles;

    private static final long serialVersionUID = 1L;

}