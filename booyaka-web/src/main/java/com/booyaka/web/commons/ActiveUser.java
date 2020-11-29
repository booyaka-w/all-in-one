package com.booyaka.web.commons;

import java.io.Serializable;
import java.util.List;

import com.booyaka.web.system.model.SysMenu;
import com.booyaka.web.system.model.UserInfo;

/**
 * @author booyaka
 * @date 2018年5月25日 下午10:51:35 TODO用户身份信息，存入session
 *       由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 */
public class ActiveUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2056009758245387630L;

	private UserInfo userInfo;

	private List<SysMenu> menusList;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<SysMenu> getMenusList() {
		return menusList;
	}

	public void setMenusList(List<SysMenu> menusList) {
		this.menusList = menusList;
	}

}
