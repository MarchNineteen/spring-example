package com.wyb.exception.exception;


import com.wyb.exception.result.BizResultEnum;

/**
 * 基础业务处理异常类
 *
 * @author Kunzite
 */
public class BizException extends RuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = -5353102099168631668L;

    /** 结果码 */
    private String code             = "UNKNOWN";

    /** 数值型结果码 */
    private int               codeNumber       = 101;

    /** 异常信息 */
    private String message          = "未知异常";

    /**
     * 构造方法
     */
    protected BizException() {
    }

    /**
     * 构造方法
     */
    protected BizException(String message) {
        this.message = message;
        this.code = BizResultEnum.LOGIC_ERROR.getCode();
    }

    /**
     * 构造方法
     * 
     * @param resultCode 错误码
     */
    public BizException(BizResultEnum resultCode) {
        this.code = resultCode.getCode();
        this.codeNumber = resultCode.getCodeNumber();
        this.message = resultCode.getDescription();
    }

    /**
     * 构造方法
     * @param resultCode	错误码
     * @param appendMsg		补充错误信息
     */
    public BizException(BizResultEnum resultCode, String appendMsg) {
        this.code = resultCode.getCode();
        this.codeNumber = resultCode.getCodeNumber();
        this.message = resultCode.getDescription() + ", " + appendMsg;
    }

    /**
     * 构造方法
     */
    public BizException(String code, int codeNumber, String message) {
        this.code = code;
        this.codeNumber = codeNumber;
        this.message = message;
    }

    /**
     * 构造方法
     * 
     * @param cause
     */
    public BizException(Throwable cause) {
        super("", cause);
    }

    /**
     * 构造方法
     * 
     * @param cause
     */
    public BizException(BizResultEnum resultCode, Throwable cause) {
        super("", cause);
        this.code = resultCode.getCode();
        this.codeNumber = resultCode.getCodeNumber();
        this.message = resultCode.getDescription();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCodeNumber() {
        return codeNumber;
    }

}
