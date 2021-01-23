/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.wyb.login.core.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class CaptchaResponse implements Serializable {

    private static final long serialVersionUID = 8445617032523881407L;

    private String repCode;

    private String repMsg;

    private Object repData;

    public CaptchaResponse() {
        this.repCode = CaptchaRepCodeEnum.SUCCESS.getCode();
    }

    public CaptchaResponse(CaptchaRepCodeEnum CaptchaRepCodeEnum) {
        this.setCaptchaRepCodeEnum(CaptchaRepCodeEnum);
    }

    // 成功
    public static CaptchaResponse success() {
        return CaptchaResponse.successMsg("成功");
    }

    public static CaptchaResponse successMsg(String message) {
        CaptchaResponse CaptchaResponse = new CaptchaResponse();
        CaptchaResponse.setRepMsg(message);
        return CaptchaResponse;
    }

    public static CaptchaResponse successData(Object data) {
        CaptchaResponse CaptchaResponse = new CaptchaResponse();
        CaptchaResponse.setRepCode(CaptchaRepCodeEnum.SUCCESS.getCode());
        CaptchaResponse.setRepData(data);
        return CaptchaResponse;
    }

    // 失败
    public static CaptchaResponse errorMsg(CaptchaRepCodeEnum message) {
        CaptchaResponse CaptchaResponse = new CaptchaResponse();
        CaptchaResponse.setCaptchaRepCodeEnum(message);
        return CaptchaResponse;
    }

    public static CaptchaResponse errorMsg(String message) {
        CaptchaResponse CaptchaResponse = new CaptchaResponse();
        CaptchaResponse.setRepCode(CaptchaRepCodeEnum.ERROR.getCode());
        CaptchaResponse.setRepMsg(message);
        return CaptchaResponse;
    }

    public static CaptchaResponse errorMsg(CaptchaRepCodeEnum CaptchaRepCodeEnum, String message) {
        CaptchaResponse CaptchaResponse = new CaptchaResponse();
        CaptchaResponse.setRepCode(CaptchaRepCodeEnum.getCode());
        CaptchaResponse.setRepMsg(message);
        return CaptchaResponse;
    }

    public static CaptchaResponse exceptionMsg(String message) {
        CaptchaResponse CaptchaResponse = new CaptchaResponse();
        CaptchaResponse.setRepCode(CaptchaRepCodeEnum.EXCEPTION.getCode());
        CaptchaResponse.setRepMsg(CaptchaRepCodeEnum.EXCEPTION.getDesc() + ": " + message);
        return CaptchaResponse;
    }

    // @Override
    // public String toString() {
    // return ToStringBuilder.reflectionToString(this);
    // }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

    public boolean isError() {
        return !isSuccess();
    }

    public boolean isSuccess() {
        return StringUtils.equals(this.repCode, CaptchaRepCodeEnum.SUCCESS.getCode());
    }

    public String getRepCode() {
        return repCode;
    }

    public void setRepCode(String repCode) {
        this.repCode = repCode;
    }

    public void setCaptchaRepCodeEnum(CaptchaRepCodeEnum CaptchaRepCodeEnum) {
        this.repCode = CaptchaRepCodeEnum.getCode();
        this.repMsg = CaptchaRepCodeEnum.getDesc();
    }

    public String getRepMsg() {
        return repMsg;
    }

    public void setRepMsg(String repMsg) {
        this.repMsg = repMsg;
    }

    public Object getRepData() {
        return repData;
    }

    public void setRepData(Object repData) {
        this.repData = repData;
    }

}
