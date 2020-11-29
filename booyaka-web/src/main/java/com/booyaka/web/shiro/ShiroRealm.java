package com.booyaka.web.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
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

import com.booyaka.web.system.model.SysMenu;
import com.booyaka.web.system.model.SysRole;
import com.booyaka.web.system.model.SysUserRole;
import com.booyaka.xtreme.commons.GlobalVariable;
import com.booyaka.xtreme.commons.ResponseModel;
import com.booyaka.xtreme.web.system.service.MenuServiceClient;
import com.booyaka.xtreme.web.system.service.RoleMenuServiceClient;
import com.booyaka.xtreme.web.system.service.RoleServiceClient;
import com.booyaka.xtreme.web.system.service.UserRoleServiceClient;
import com.booyaka.xtreme.web.system.service.UserServiceClient;

/**
 * shiro权限认证
 */
public class ShiroRealm extends AuthorizingRealm {

	private static Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired
	UserServiceClient sysUserService;

	@Autowired
	UserRoleServiceClient sysUserRoleService;

	@Autowired
	RoleMenuServiceClient sysRoleMenuService;

	@Autowired
	MenuServiceClient sysMenuService;

	@Autowired
	RoleServiceClient sysRoleService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		LOGGER.info("-----------------------------------Shiro开始登录认证-------------------------------------------");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		ResponseModel sysUser = sysUserService.queryByUserName(token.getUsername());
		if (sysUser == null) {
			throw new UnknownAccountException();
		}

		if (GlobalVariable.ACCOUNT_STATUS.LOCK == sysUser.getUserStatus()) {
			throw new LockedAccountException();
		}

		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setSysUserId(sysUser.getUserId());
		List<SysUserRole> surlist = sysUserRoleService.selectSelective(sysUserRole);
		if (surlist.size() != 0) {
			for (SysUserRole userRole : surlist) {
				SysRole sysRole = sysRoleService.selectByPrimaryKey(userRole.getSysRoleId());
				if (sysRole.getRoleStatus() == 2) {
					throw new DisabledAccountException();
				}
			}
		}
		return new SimpleAuthenticationInfo(sysUser.getUserName(), sysUser.getPassword(),
				ByteSource.Util.bytes(sysUser.getSalt()), this.getName());
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
		List<SysMenu> buttonList = sysMenuService.queryButtonByUserId(activeUser.getUser().getUserId());
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
