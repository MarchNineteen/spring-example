package com.wyb.exception.result;

import com.alibaba.fastjson.JSONObject;
import com.wyb.exception.exception.BizException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 构造函数，转化BizResultCodeEnum，同时attach data
     *
     * @param codeEnum
     * @param data
     */
    public WebResult(WebResultEnum codeEnum, T data) {
        this(codeEnum);
        this.data = data;
    }


    /**
     * 构造函数，转化BizResultCodeEnum对象
     *
     * @param codeEnum 错误码
     */
    public WebResult(BizResultEnum codeEnum) {
        this(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription(), codeEnum == BizResultEnum.SUCCESS);
    }

    /**
     * 构造函数，转化BizResultCodeEnum，同时attach data
     *
     * @param codeEnum
     * @param data
     */
    public WebResult(BizResultEnum codeEnum, T data) {
        this(codeEnum);
        this.data = data;
    }

    /**
     * 转化业务返回对象
     *
     * @param bizResult
     * @return
     */
    public static <T> WebResult<T> convertBizResult(BizResult<T> bizResult) {
        WebResult<T> webResult = new WebResult<T>(String.valueOf(bizResult.getCodeNumber()),
                bizResult.getMessage(), bizResult.isSuccess());
        if (null != bizResult.getResultObj()) {
            webResult.setData(bizResult.getResultObj());
        }
        return webResult;
    }

    public static <T> WebResult<T> success(T object) {
        WebResult<T> result = new WebResult<T>(BizResultEnum.SUCCESS);
        result.setSuccess(true);
        if (object != null) {
            result.setData(object);
        }
        return result;
    }

    public static <T> WebResult<T> success() {
        return success(null);
    }

    /**
     * 转化业务返回对象
     *
     * @param bizResult
     * @return
     */
    public static WebResult convertBizResultMapType(BizResult<Map<String, Object>> bizResult) {
        return new WebResult(bizResult.getCode(), bizResult.getMessage(), bizResult.isSuccess());
    }

    /**
     * 转化BizException对象
     *
     * @param bizEx
     * @return
     */
    public static WebResult convertBizException(BizException bizEx) {
        return new WebResult(String.valueOf(bizEx.getCodeNumber()), bizEx.getMessage(), false);
    }

    public static <T> WebResult<T> illegalArgument(String field, String message) {
        WebResult<T> result = new WebResult<T>(BizResultEnum.ILLEGAL_ARGUMENT);
        List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
        errorMessages.add(new ErrorMessage(field, "", message));
        result.setErrorMessage(errorMessages);
        return result;
    }

    /**
     * 增加错误信息
     *
     * @param field
     * @param bizEx
     */
    public void addFieldError(String field, BizException bizEx) {

        if (errorMessage == null) {
            errorMessage = new ArrayList<ErrorMessage>();
        }
        errorMessage.add(new ErrorMessage(field, bizEx.getCode(), bizEx.getMessage()));
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
