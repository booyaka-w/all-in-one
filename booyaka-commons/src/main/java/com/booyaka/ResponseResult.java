package com.booyaka;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.booyaka.emuns.ResponseEnum;

public class ResponseResult {

	/**
	 * 状态标识
	 */
	private boolean state = ResponseEnum.SUCCESS.getState();

	/**
	 * 状态码
	 */
	private int code = ResponseEnum.SUCCESS.getCode();

	/**
	 * 响应信息
	 */
	private String message = ResponseEnum.SUCCESS.getMessage();

	/**
	 * 单数据信息结果集
	 */
	private Map<String, Object> dataMap;

	/**
	 * 列表数据信息结果集
	 */
	private List<Map<String, Object>> listMap;

	public ResponseResult() {

	}

	public ResponseResult(ResponseEnum response) {
		this.state = response.getState();
		this.code = response.getCode();
		this.message = response.getMessage();
	}

	public ResponseResult(ResponseEnum response, String message) {
		this.state = response.getState();
		this.code = response.getCode();
		if (StringUtils.isBlank(response.getMessage())) {
			this.message = message;
		} else {
			this.message = response.getMessage() + ":" + message;
		}
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public List<Map<String, Object>> getListMap() {
		return listMap;
	}

	public void setListMap(List<Map<String, Object>> listMap) {
		this.listMap = listMap;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
