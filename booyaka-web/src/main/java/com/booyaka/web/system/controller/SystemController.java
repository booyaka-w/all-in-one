package com.booyaka.web.system.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.booyaka.ResponseResult;
import com.booyaka.emuns.ResponseEnum;
import com.booyaka.web.commons.ActiveUser;
import com.booyaka.web.system.model.SysMenu;
import com.booyaka.web.system.model.UserInfo;
import com.booyaka.web.system.service.SysMenuService;
import com.booyaka.web.system.service.UserInfoService;

import cn.amorou.uid.UidGenerator;

/**
 * TODO Controller
 *
 * @author booyaka
 * @date 2020-11-27 14:05:36
 */
@RestController
@RequestMapping("/system")
public class SystemController {

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	SysMenuService menuService;

	@Autowired
	private UidGenerator uidGenerator;

	@GetMapping("/login")
	public ModelAndView loginPage() {

		System.err.println(uidGenerator.getUID());
		System.err.println(uidGenerator.parseUID(uidGenerator.getUID()));

		return new ModelAndView("login");
	}

	@GetMapping("/index")
	public ModelAndView indexPage() {
		return new ModelAndView("index");
	}

	@PostMapping("/dologin")
	public ResponseResult doLogin(String userName, String password) {
		ResponseResult result = new ResponseResult(ResponseEnum.SUCCESS);
		UserInfo userInfo = userInfoService.queryByUserName(userName);
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			subject.login(token);
			ActiveUser activeUser = new ActiveUser();
			List<SysMenu> modelList = menuService.queryModelByUserId(userInfo.getUserId());
			for (SysMenu sysMenu : modelList) {
				List<SysMenu> menuList = menuService.queryMenuByUserId(userInfo.getUserId(), sysMenu.getMenuId());
				sysMenu.setChildList(menuList);
			}
			activeUser.setMenusList(modelList);
			activeUser.setUserInfo(userInfo);
			SecurityUtils.getSubject().getSession().setAttribute("activeUser", activeUser);
		} catch (UnknownAccountException e) {
			e.getStackTrace();
			return new ResponseResult(ResponseEnum.ERROR, "账号不存在");
		} catch (IncorrectCredentialsException e) {
			e.getStackTrace();
			return new ResponseResult(ResponseEnum.ERROR, "账号或密码错误");
		} catch (LockedAccountException e) {
			e.getStackTrace();
			return new ResponseResult(ResponseEnum.ERROR, "账号已被禁用");
		} catch (DisabledAccountException e) {
			e.getStackTrace();
			return new ResponseResult(ResponseEnum.ERROR, "该身份已被禁用");
		} catch (RuntimeException e) {
			e.getStackTrace();
			return new ResponseResult(ResponseEnum.ERROR, "未知错误,请联系管理员");
		}
		return result;
	}

}