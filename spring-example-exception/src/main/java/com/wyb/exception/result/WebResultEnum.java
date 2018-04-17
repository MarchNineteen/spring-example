package com.wyb.exception.result;


/**
 * @author Kunzite
 */

public enum WebResultEnum {

    /* 操作成功 */
    SUCCESS("SUCCESS", 1000, "操作成功"),

    /* 系统错误 */
    SYSTEM_FAILURE("SYSTEM_FAILURE", 1001, "系统错误，稍后再试"),

    /* 参数为空 */
    NULL_ARGUMENT("NULL_ARGUMENT", 1002, "参数为空"),

    /* 参数不正确 */
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", 1003, "参数不正确"),

    /* 逻辑错误 */
    LOGIC_ERROR("LOGIC_ERROR", 1004, "逻辑错误"),

    /* 认证失败 */
    AUTHORIZE_FAILURE("AUTHORIZE_FAILURE", 1005, "用户认证失败"),

    /* 校验失败 */
    VALIDATE_FAILURE("VALIDATE_FAILURE", 1006, "校验失败"),

    /* 数据不存在 */
    NOT_EXISTED("NOT_EXISTED", 1007, "数据不存在"),

    /* 数据异常 */
    DATA_EXCEPTION("DATA_EXCEPTION", 1008, "数据异常"),

    /* 服务器内部异常 */
    SERVER_EXCEPTION("SERVER_EXCEPTION", 1009, "服务器内部异常"),

    /* 验证码有误 */
    PHONECODE_ERROR("PHONECODE_ERROR", 1010, "验证码有误"),


    /* 暂无信息 */
    NO_LIST_DATA("NO_LIST_DATA", 3000, "暂无信息展示"),

    /*插入数据库失败*/
    INSERT_FAILURE("INSERT_FAILURE", 3001, "插入数据库失败"),

    /*更新数据库失败*/
    UPDATE_FAILURE("UPDATE_FAILURE", 3002, "插入数据库失败");


    /**
     * 枚举值
     */
    private final String code;

    /**
     * 数值型错误码
     */
    private final int codeNumber;

    /**
     * 枚举信息
     */
    private final String description;

    /**
     * 构造函数
     *
     * @param code        枚举值
     * @param codeNumber  数值型错误码
     * @param description 枚举信息
     */
    WebResultEnum(String code, int codeNumber, String description) {
        this.code = code;
        this.codeNumber = codeNumber;
        this.description = description;
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

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getCodeNumber() {
        return codeNumber;
    }


}
