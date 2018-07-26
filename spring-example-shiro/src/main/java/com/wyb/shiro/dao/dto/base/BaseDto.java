package com.wyb.shiro.dao.dto.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kunzite
 */
@Data
public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = 4238308494801159506L;

    private Long id;

    /**
     * 是否删除 0正常 1删除
     */
    private Integer isDelete;

    private Date createTime;

    private Date updateTime;
}
