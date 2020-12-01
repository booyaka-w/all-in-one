package com.booyaka.web.system.model;

import java.util.Date;

import com.booyaka.BaseModel;

/**
 * TODO 用户信息表Model
 *
 * @author booyaka
 * @date 2020-12-01 13:45:57
 */
public class SysUserInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4231826998216525294L;

	/**
	 * 主键ID
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

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 用户头像
	 */
	private String userHeadimg;

	/**
	 * 电话
	 */
	private String userPhone;

	/**
	 * 邮箱
	 */
	private String userEmail;

	/**
	 * 用户所属
	 */
	private String userSubordinate;

	/**
	 * 是否锁定，1未锁定，2锁定
	 */
	private Integer userState;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 修改人
	 */
	private String updateUser;

	/**
	 * 版本号
	 */
	private Integer version;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserHeadimg() {
		return userHeadimg;
	}

	public void setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserSubordinate() {
		return userSubordinate;
	}

	public void setUserSubordinate(String userSubordinate) {
		this.userSubordinate = userSubordinate;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		if (nickName != null) {
			sb.append(", nickName=").append(nickName);
		}
		if (userHeadimg != null) {
			sb.append(", userHeadimg=").append(userHeadimg);
		}
		if (userPhone != null) {
			sb.append(", userPhone=").append(userPhone);
		}
		if (userEmail != null) {
			sb.append(", userEmail=").append(userEmail);
		}
		if (userSubordinate != null) {
			sb.append(", userSubordinate=").append(userSubordinate);
		}
		if (userState != null) {
			sb.append(", userState=").append(userState);
		}
		if (remark != null) {
			sb.append(", remark=").append(remark);
		}
		if (createDate != null) {
			sb.append(", createDate=").append(createDate);
		}
		if (createUser != null) {
			sb.append(", createUser=").append(createUser);
		}
		if (updateDate != null) {
			sb.append(", updateDate=").append(updateDate);
		}
		if (updateUser != null) {
			sb.append(", updateUser=").append(updateUser);
		}
		if (version != null) {
			sb.append(", version=").append(version);
		}
		sb.append("]");
		return sb.toString();
	}
}