package com.booyaka.web.system.model;

import java.util.Date;

import com.booyaka.BaseModel;

/**
 * TODO 用户角色关系表Model
 *
 * @author booyaka
 * @date 2020-11-28 22:01:21
 */
public class SysUserRole extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9127598688922273285L;

	/**
	 * 主键ID
	 */
	private String userRoleId;

	/**
	 * 用户ID
	 */
	private String sysUserId;

	/**
	 * 权限ID
	 */
	private String sysRoleId;

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

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
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

	/**
	 * ToString
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		if (userRoleId != null) {
			sb.append(", userRoleId=").append(userRoleId);
		}
		if (sysUserId != null) {
			sb.append(", sysUserId=").append(sysUserId);
		}
		if (sysRoleId != null) {
			sb.append(", sysRoleId=").append(sysRoleId);
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
		sb.append("]");
		return sb.toString();
	}
}