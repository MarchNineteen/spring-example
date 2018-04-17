package com.wyb.exception.result;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 返回对象基类
 *
 * @author Kunzite
 */
public class BaseResult implements ResultConstant, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5935701309155834067L;

    /**
     * 成功标识
     */
    private boolean success = false;

    /**
     * 结果码
     */
    private String code = "UNKNOWN";

    /**
     * 数值型结果码
     */
    private int codeNumber = 101;

    /**
     * 返回信息
     */
    private String message = "未知异常";

    /**
     * 默认构造函数
     */
    public BaseResult() {

    }

    /**
     * 构造函数
     *
     * @param code
     * @param codeNumber
     * @param message
     */
    public BaseResult(String code, int codeNumber, String message) {
        this.code = code;
        this.codeNumber = codeNumber;
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param success 成功标示
     * @param code    返回值code
     */
    public BaseResult(boolean success, String code, int codeNumber,
                      String description) {
        this.success = success;
        this.code = code;
        this.codeNumber = codeNumber;
        this.message = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(int codeNumber) {
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
