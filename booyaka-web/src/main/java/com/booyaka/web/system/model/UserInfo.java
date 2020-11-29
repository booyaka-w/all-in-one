package com.booyaka.web.system.model;

import com.booyaka.BaseModel;

/**
 * TODO Model
 *
 * @author booyaka
 * @date 2020-11-27 14:05:36
 */
public class UserInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String userId;

	/**
	 * 账号
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ToString
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		if (userId != null) {
			sb.append(", userId=").append(userId);
		}
		if (userName != null) {
			sb.append(", userName=").append(userName);
		}
		if (password != null) {
			sb.append(", password=").append(password);
		}
		sb.append("]");
		return sb.toString();
	}
}