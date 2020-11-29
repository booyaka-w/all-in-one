package com.booyaka.web.shiro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.booyaka.web.system.model.SysMenu;
import com.booyaka.web.system.service.SysMenuService;

/**
 * 权限 拦截策略
 */
@Component
public class UrlPathMatchingFilter extends PathMatchingFilter {

	private static UrlPathMatchingFilter urlPathMatchingFilter;

	@PostConstruct
	public void init() {
		urlPathMatchingFilter = this;
		urlPathMatchingFilter.sysMenuService = this.sysMenuService;
	}

	@Autowired
	SysMenuService sysMenuService;

	/**
	 * 如果返回false表示拦截不继续执行handler，如果返回true表示放行
	 */
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String requestUrl = getPathWithinApplication(request);
		System.out.println("请求的url :" + requestUrl);
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			if (requestUrl.endsWith("/url")) {
				WebUtils.issueRedirect(request, response, "/unauthorized");
				return false;
			}
			return true;
		}
		ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getSession().getAttribute("activeUser");
		String userId = activeUser.getUser().getUserId();
		List<SysMenu> menuList = urlPathMatchingFilter.sysMenuService.queryPathByUserId(userId);
		boolean hasPermission = false;
		for (SysMenu menu : menuList) {
			if (menu.getMenuPath().equals(requestUrl)) {
				hasPermission = true;
				break;
			}
		}
		if (hasPermission) {
			return true;
		} else {
			UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径" + requestUrl + "的权限");
			subject.getSession().setAttribute("ex", ex);
			WebUtils.issueRedirect(request, response, "/unauthorized");
			return false;
		}
	}
}
