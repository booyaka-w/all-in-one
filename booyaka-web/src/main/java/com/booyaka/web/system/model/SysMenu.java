package com.booyaka.web.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.booyaka.BaseModel;

/**
 * TODO 菜单信息表Model
 *
 * @author booyaka
 * @date 2020-12-01 13:45:31
 */
public class SysMenu extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1341346560562495991L;

	/**
	 * 主键ID
	 */
	private String menuId;

	/**
	 * 模块名/菜单名/按钮
	 */
	private String menuName;

	/**
	 * 模块图标
	 */
	private String menuIcon;

	/**
	 * 菜单路径/按钮标识
	 */
	private String menuPath;

	/**
	 * 状态：1启用，2禁用
	 */
	private Integer menuStatus;

	/**
	 * 类型：1模块，2菜单，3按钮
	 */
	private Integer menuType;

	/**
	 * 菜单所属
	 */
	private Integer menuSubordinate;

	/**
	 * 排序号
	 */
	private Integer menuSort;

	/**
	 * 上级ID
	 */
	private String parentId;

	/**
	 * 访问资源
	 */
	private String resource;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 修改人
	 */
	private String updateUser;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 版本号
	 */
	private Integer version;

	private List<SysMenu> childList = new ArrayList<SysMenu>();

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public Integer getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuSubordinate() {
		return menuSubordinate;
	}

	public void setMenuSubordinate(Integer menuSubordinate) {
		this.menuSubordinate = menuSubordinate;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<SysMenu> getChildList() {
		return childList;
	}

	public void setChildList(List<SysMenu> childList) {
		this.childList = childList;
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
		if (menuId != null) {
			sb.append(", menuId=").append(menuId);
		}
		if (menuName != null) {
			sb.append(", menuName=").append(menuName);
		}
		if (menuIcon != null) {
			sb.append(", menuIcon=").append(menuIcon);
		}
		if (menuPath != null) {
			sb.append(", menuPath=").append(menuPath);
		}
		if (menuStatus != null) {
			sb.append(", menuStatus=").append(menuStatus);
		}
		if (menuType != null) {
			sb.append(", menuType=").append(menuType);
		}
		if (menuSubordinate != null) {
			sb.append(", menuSubordinate=").append(menuSubordinate);
		}
		if (menuSort != null) {
			sb.append(", menuSort=").append(menuSort);
		}
		if (parentId != null) {
			sb.append(", parentId=").append(parentId);
		}
		if (resource != null) {
			sb.append(", resource=").append(resource);
		}
		if (createUser != null) {
			sb.append(", createUser=").append(createUser);
		}
		if (updateUser != null) {
			sb.append(", updateUser=").append(updateUser);
		}
		if (createDate != null) {
			sb.append(", createDate=").append(createDate);
		}
		if (updateDate != null) {
			sb.append(", updateDate=").append(updateDate);
		}
		if (version != null) {
			sb.append(", version=").append(version);
		}
		sb.append("]");
		return sb.toString();
	}
}