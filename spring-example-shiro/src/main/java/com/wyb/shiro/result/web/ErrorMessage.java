package com.wyb.shiro.result.web;


import org.springframework.util.StringUtils;

/**
 * 错误消息对象
 *
 * @author Kunzite
 */
public class ErrorMessage implements Comparable<ErrorMessage> {

	/**
	 * 出错的property
	 */
	private String field;

	/**
	 * 错误码
	 */
	private String code;

	/**
	 * 提示信息
	 */
	private String message;

	/**
	 * 构造方法
	 *
	 * @param field
	 * @param message
	 */
	public ErrorMessage(String field, String code, String message) {
		this.field = field;
		this.code = code;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(ErrorMessage errMsg) {
		if (!StringUtils.isEmpty(errMsg.getField()) && !StringUtils.isEmpty(this.field)) {
			return errMsg.getField().compareTo(field);
		}
		return 0;
	}
}
