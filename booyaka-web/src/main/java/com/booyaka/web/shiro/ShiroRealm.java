package com.booyaka.web.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.booyaka.web.commons.ActiveUser;
import com.booyaka.web.system.model.SysMenu;
import com.booyaka.web.system.model.SysRole;
import com.booyaka.web.system.model.SysUserRole;
import com.booyaka.web.system.model.UserInfo;
import com.booyaka.web.system.service.SysMenuService;
import com.booyaka.web.system.service.SysRoleService;
import com.booyaka.web.system.service.SysUserRoleService;
import com.booyaka.web.system.service.UserInfoService;

/**
 * shiro权限认证
 */
public class ShiroRealm extends AuthorizingRealm {

	private static Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	SysUserRoleService userRoleService;

	@Autowired
	SysMenuService msenuService;

	@Autowired
	SysRoleService roleService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		LOGGER.info("-----------------------------------Shiro开始登录认证-------------------------------------------");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		UserInfo userInfo = userInfoService.queryByUserName(token.getUsername());
		if (userInfo == null) {
			throw new UnknownAccountException();
		}

		SysUserRole userRole = new SysUserRole();
		userRole.setSysUserId(userInfo.getUserId());
		List<SysUserRole> userRoleList = userRoleService.querySelective(userRole);
		if (userRoleList.size() != 0) {
			for (SysUserRole sr : userRoleList) {
				SysRole sysRole = roleService.queryByPrimaryKey(sr.getSysRoleId());
				if (sysRole.getRoleStatus() == 2) {
					throw new DisabledAccountException();
				}
			}
		}
		return new SimpleAuthenticationInfo(userInfo.getUserName(), userInfo.getPassword(), ByteSource.Util.bytes(""),
				this.getName());
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOGGER.info("-----------------------------------Shiro开授权-------------------------------------------");
		ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getSession().getAttribute("activeUser");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> buttenPermissions = new ArrayList<String>();
		List<SysMenu> buttonList = msenuService.queryButtonByUserId(activeUser.getUserInfo().getUserId());
		if (buttonList.size() > 0) {
			for (SysMenu sysMenu : buttonList) {
				buttenPermissions.add(sysMenu.getMenuPath());
			}
		}
		LOGGER.info("-----------------------------------添加按钮权限-------------------------------------------");
		info.addStringPermissions(buttenPermissions);
		return info;
	}
}
