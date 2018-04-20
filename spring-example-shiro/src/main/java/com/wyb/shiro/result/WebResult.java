package com.wyb.shiro.result;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * web层返回结果
 *
 * @author Kunzite
 */
@SuppressWarnings("rawtypes")
public class WebResult<T> implements Serializable {

    private static final long serialVersionUID = 3253038210225265084L;

    /**
     * 执行结果
     */
    private String resultCode;

    /**
     * 提示信息，会显示在页面上，标题
     */
    private String message;

    /**
     * 错误的字段信息
     */
    private List<ErrorMessage> errorMessage = new ArrayList<ErrorMessage>();

    /**
     * 成功标识
     */
    private boolean success = false;

    /**
     * 结果集
     */
    private T data;

    /**
     * 构造方法
     */
    public WebResult() {
    }


    public WebResult(Integer resultCode, String message, boolean success, T data) {
        this.resultCode = resultCode.toString();
        this.message = message;
        this.success = success;
        this.data = data;
    }

    /**
     * 新建一个对象
     *
     * @param resultCode
     * @param message
     * @param success
     */
    public WebResult(String resultCode, String message, boolean success) {
        this.resultCode = resultCode;
        this.message = message;
        this.success = success;
    }

    public WebResult(Integer resultCode, String message, boolean success) {
        this.resultCode = resultCode.toString();
        this.message = message;
        this.success = success;
    }

    /**
     * 构造函数，转化WebResultEnum对象
     *
     * @param codeEnum 错误码
     */
    public WebResult(WebResultEnum codeEnum) {
        this(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription(), codeEnum == WebResultEnum.SUCCESS);
    }

    /**
     * @param codeEnum
     * @param data
     */
    public WebResult(WebResultEnum codeEnum, T data) {
        this(codeEnum);
        this.data = data;
    }

    public static <T> WebResult<T> success(T object) {
        WebResult<T> result = new WebResult<T>(WebResultEnum.SUCCESS);
        result.setSuccess(true);
        if (object != null) {
            result.setData(object);
        }
        return result;
    }

    public static <T> WebResult<T> success() {
        return success(null);
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorMessage> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<ErrorMessage> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorMessage(final ErrorMessage errorMessage) {
        this.errorMessage = new ArrayList<ErrorMessage>() {{
            add(errorMessage);
        }};
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
