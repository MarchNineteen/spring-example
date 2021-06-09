package com.wyb.mybatis.plus.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Marcher丶
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private Integer sex;

    private Integer age;

    /**
     * 联系方式
     */
    private String phone;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
