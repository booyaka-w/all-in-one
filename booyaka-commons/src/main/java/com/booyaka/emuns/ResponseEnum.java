package com.booyaka.emuns;

public enum ResponseEnum {

	ERROR(false, 100, "操作失败"), WARNING(false, 101, "参数有误"), BUSINESS_WARNING(false, 102, null),
	SUCCESS(true, 200, "操作成功");

	/**
	 * 状态标识
	 */
	private Boolean state;

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 响应信息
	 */
	private String message;

	/**
	 * 
	 * @param state   状态标识
	 * @param code    状态码
	 * @param message 响应信息
	 */
	ResponseEnum(Boolean state, Integer code, String message) {
		this.state = state;
		this.code = code;
		this.message = message;
	}

	public Boolean getState() {
		return state;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
