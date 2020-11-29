package com.booyaka.web.system.model;

import java.util.Date;

import com.booyaka.BaseModel;

/**
 * TODO 角色菜单关系表Model
 *
 * @author booyaka
 * @date 2020-11-28 22:01:03
 */
public class SysRoleMenu extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1829757217300265023L;

	/**
	 * 主键ID
	 */
	private String roleMenuId;

	/**
	 * 权限ID
	 */
	private String sysRoleId;

	/**
	 * 菜单ID
	 */
	private String sysMenuId;

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

	public String getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getSysMenuId() {
		return sysMenuId;
	}

	public void setSysMenuId(String sysMenuId) {
		this.sysMenuId = sysMenuId;
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
		if (roleMenuId != null) {
			sb.append(", roleMenuId=").append(roleMenuId);
		}
		if (sysRoleId != null) {
			sb.append(", sysRoleId=").append(sysRoleId);
		}
		if (sysMenuId != null) {
			sb.append(", sysMenuId=").append(sysMenuId);
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