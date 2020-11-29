package com.booyaka.web.system.model;

import java.util.Date;

import com.booyaka.BaseModel;

/**
 * TODO 角色信息表Model
 *
 * @author booyaka
 * @date 2020-11-28 22:00:52
 */
public class SysRole extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2717608143931809484L;

	/**
	 * 主键ID
	 */
	private String roleId;

	/**
	 * 角色名
	 */
	private String roleName;

	/**
	 * 是否可用,1：可用，2不可用
	 */
	private Integer roleStatus;

	/**
	 * 角色所属
	 */
	private Integer roleSubordinate;

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getRoleSubordinate() {
		return roleSubordinate;
	}

	public void setRoleSubordinate(Integer roleSubordinate) {
		this.roleSubordinate = roleSubordinate;
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
		if (roleId != null) {
			sb.append(", roleId=").append(roleId);
		}
		if (roleName != null) {
			sb.append(", roleName=").append(roleName);
		}
		if (roleStatus != null) {
			sb.append(", roleStatus=").append(roleStatus);
		}
		if (roleSubordinate != null) {
			sb.append(", roleSubordinate=").append(roleSubordinate);
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