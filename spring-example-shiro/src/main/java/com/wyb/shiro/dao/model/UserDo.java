package com.wyb.shiro.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Kunzite
 */
@Data
@Builder(toBuilder=true)
@Table(name = "shiro.user")
@NoArgsConstructor
@AllArgsConstructor
public class UserDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * 密码盐
     */
    private String salt;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系方式
     */
    private String phone;

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
    public UserDo init() {
        this.setIsDelete(0);
        this.setCreateTime(new Date());
        this.setUpdateTime(new Date());
        return this;
    }
}