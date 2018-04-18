package com.wyb.shiro.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kunzite
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1618357536992740779L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "is_delete")
    protected Integer isDelete = 0;

    @Column(name = "create_time")
    protected Date createTime;

    @Column(name = "update_time")
    protected Date updateTime;

}
