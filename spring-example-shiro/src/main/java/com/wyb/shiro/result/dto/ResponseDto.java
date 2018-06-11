package com.wyb.shiro.result.dto;


import com.wyb.shiro.result.BaseEnum;

import java.io.Serializable;

/**
 * ResponseDto
 *
 * @author Kunziye
 */
public class ResponseDto<T> implements Serializable {

    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = 1;
    private static final long serialVersionUID = 4082846602141879024L;
    private boolean status = true;

    private String msg;

    private Exception exception;

    private Integer errorCode;

    private BaseEnum error;

    private T data;

    public static <T> ResponseDto<T> success() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setData(null);
        response.setErrorCode(SUCCESS);
        response.setMsg("请求成功");
        return response;
    }

    public static <T> ResponseDto<T> success(T data) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setData(data);
        response.setErrorCode(SUCCESS);
        response.setMsg("请求成功");
        return response;
    }

    public static <T> ResponseDto<T> error(BaseEnum errorCode) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setErrorCode(errorCode.getCode());
        response.setMsg(errorCode.getMessage());
        response.setError(errorCode);
        response.setStatus(false);
        return response;
    }

    public boolean isSuccess() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * @param exception the exception to set
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public BaseEnum getError() {
        return error;
    }

    public void setError(BaseEnum error) {
        this.error = error;
    }
}
