package com.wyb.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Marcher丶
 */
@Document(indexName = "testindex")
public class LoginLogDO implements Serializable {
    private static final long serialVersionUID = 1968078627543431790L;

    @Id
    private long id;// 主键ID

    private long userId; // 用户id
    private long agencyId; //

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Field(name = "loginTime")
    private long loginTime; // 登录时间

    private String loginIp; // 登录ip

    private String loginAddress;// 登录地区

    private String regionCode;// 行政区划码

    private String isp;// 运营商
//    private SourceComeFromType source;

    public LoginLogDO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(long agencyId) {
        this.agencyId = agencyId;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

//    public SourceComeFromType getSource() {
//        SourceComeFromType sourceComeFromType =
//        return SourceComeFromType.get(source);
//    }
//
//    public void setSource(SourceComeFromType source) {
//        this.source = source;
//    }
}

