package com.wyb.shiro.result.web;


import com.wyb.shiro.result.BaseEnum;

/**
 * controller层返回枚举
 *
 * @author Kunzite
 */
public enum WebResultEnum implements BaseEnum {

    /* 操作成功 */
    SUCCESS(1000, "操作成功"),
    /* 操作成功 */
    ERROR_PARAMS(1001, "参数错误");

    /**
     * 数值型错误码
     */
    private int code;

    /**
     * 枚举信息
     */
    private String message;

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误信息
     */
    WebResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取枚举
     *
     * @param code
     * @return
     */
    public static WebResultEnum getByCode(String code) {
        for (WebResultEnum item : values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
