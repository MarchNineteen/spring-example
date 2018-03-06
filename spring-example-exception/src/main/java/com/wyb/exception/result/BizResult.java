package com.wyb.exception.result;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 返回业务结果
 * @author Kunzite
 */
public class BizResult<T> extends BaseResult {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5935703301155834061L;

	/**
	 * 返回结果实例。
	 */
	private T resultObj;

	/**
	 * 默认构造函数
	 */
	@SuppressWarnings("unused")
	private BizResult() {
	}

	/**
	 * 构造函数
	 *
	 * @param code
	 * @param codeNumber
	 * @param message
	 */
	public BizResult(String code, int codeNumber, String message) {
		super(code, codeNumber, message);
	}

	/**
	 * 构造方法
	 *
	 * @param success
	 *            成功标示
	 * @param resultCode
	 *            返回值code
	 */
	public BizResult(boolean success, String resultCode, int codeNumber,
                     String description) {
		super(success, resultCode, codeNumber, description);
	}

	/**
	 * 构造方法
	 *
	 * @param resultCode
	 */
	public BizResult(BizResultEnum resultCode, String extDesc) {
		if (StringUtils.isNotBlank(extDesc)) {
			this.setMessage(resultCode.getDescription() + "," + extDesc);
		}
	}

	/**
	 * 构造方法
	 *
	 * @param resultCode
	 */
	public BizResult(BizResultEnum resultCode) {
		this.setSuccess(resultCode == BizResultEnum.SUCCESS);
		this.setCode(resultCode.getCode());
		this.setCodeNumber(resultCode.getCodeNumber());
		this.setMessage(resultCode.getDescription());
	}

	/**
	 * 构造方法
	 *
	 * @param resultCode
	 */
	public BizResult(boolean success,BizResultEnum resultCode) {
		this.setSuccess(success);
		this.setCode(resultCode.getCode());
		this.setCodeNumber(resultCode.getCodeNumber());
		this.setMessage(resultCode.getDescription());
	}


	/**
	 * 构造方法
	 *
	 * @param resultCode
	 */
	public BizResult(BizResultEnum resultCode, T resultObj) {
		this.setSuccess(resultCode == BizResultEnum.SUCCESS);
		this.setCode(resultCode.getCode());
		this.setCodeNumber(resultCode.getCodeNumber());
		this.setMessage(resultCode.getDescription());
		this.resultObj = resultObj;
	}

	/**
	 * 返回默认成功对象
	 *
	 * @return
	 */
	public static <T> BizResult<T> success() {
		return success(null);
	}

	public static <T> BizResult<T> success(T resultObj) {
		return new BizResult<T>(BizResultEnum.SUCCESS, resultObj);
	}

	public T getResultObj() {
		return resultObj;
	}

	public void setResultObj(T resultObj) {
		this.resultObj = resultObj;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
