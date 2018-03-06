package com.wyb.exception.result;


/**
 * 操作返回码枚举
 *
 * @author Kunzite
 */
public enum BizResultEnum {

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
	PHONECODE_ERROR("PHONECODE_ERROR", 1010, "验证码有误,或者验证码已经过期"),

	/* session 过期或未登录 */
	SESSION_MISS("SESSION_MISS", 1011, "请先登录!"),

	/* 企业用户已经存在不能重复添加 */
	ENTERPRISE_EXISTED("ENTERPRISE_EXISTED", 2001, "企业用户已经存在不能重复添加"),

	/* 用户名或者密码错误 */
	LOGIN_USERNAME_PASSWORD_ERROR("LOGIN_USERNAME_PASSWORD_ERROR", 2002, "用户名或者密码错误"),

	/* 用户不存在 */
	LOGIN_USERNAME_NOT_EXIST("LOGIN_USERNAME_NOT_EXIST", 2003, "用户不存在"),

	/*用户存在*/
	LOGIN_USERNAME_EXIST("LOGIN_USERNAME_EXIST", 2013, "用户已存在"),

	/**用户账号异常或被删除**/
	LOGIN_IS_DELETE("LOGIN_IS_DELETE",2006,"账号异常，请联系管理员"),

	/**用户账号异常或被删除**/
	LOGIN_IS_CHECK("LOGIN_IS_CHECK",2110,"您的信息正在审核中"),

	/**更新成功**/
	UPDATE_SUCCESS("UPDATE_SUCCESS",2007,"更新成功"),

	/**更新失败**/
	UPDATE_FALSE("UPDATE_FALSE",2008,"更新失败"),

	/* 添加失败*/
	ADD_ERROR("ADD_ERROR",2004,"添加失败"),

	/* 添加成功*/
	ADD_SUCCESS("ADD_SUCCESS",2005,"添加成功"),

	/* 获取验证码失败，请稍后重试*/
	GET_AUTHCODE_FALSE("GET_AUTHCODE_FALSE",2006,"接收验证码失败，请稍后重试"),

	/**
	 * 报名人数超过最大参加人数
	 */
	ACTIVITY_APPLY_USERNUM_OVER("ACTIVITY_APPLY_USERNUM_OVER", 3005, "报名人数超过最大参加人数"),

	/**用户已经报名了该活动**/
	ACTIVITY_APPLY_SELF_REPEAT("ACTIVITY_APPLY_SELF_REPEAT", 3006, "用户已经报名了该活动"),

	/**
	 * 一个订单ordersn对应了多条数据
	 */
	ACTIVITY_APPLY_ORDERSN_REPEAT("ACTIVITY_APPLY_ORDERSN_REPEAT", 3003, "一个订单orderSn对应了多条数据"),


	WX_OPENID_NO_HAS("WX_OPENID_NO_HAS",4000,"未获取到微信openId或已过期"),

	SUPER_NOT_DEL("SUPER_NOT_DEL",4003,"不允许删除"),

	WEB_REFRESH("WEB_REFRESH",4001,"web页面刷新请求"),
	/**
	 * 一个订单ordersn对应了多条数据
	 */
	ACTIVITY_INVALID("ACTIVITY_INVALID", 3001, "活动已过期"),
	SMS_BAN_TIME("SMS_BAN_TIME",3500,"短信接口请求频繁，请稍后重试"),
	SMS_SEND_ERROR("SMS_SEND_ERROR",3500,"短信接口请求失败");
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
	 * @param code
	 *            枚举值
	 * @param codeNumber
	 *            数值型错误码
	 * @param description
	 *            枚举信息
	 */
	BizResultEnum(String code, int codeNumber, String description) {
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
	public static BizResultEnum getByCode(String code) {
		for (BizResultEnum item : values()) {
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
