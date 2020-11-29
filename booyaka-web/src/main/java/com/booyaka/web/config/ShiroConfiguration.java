package com.booyaka.web.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.booyaka.web.shiro.ShiroJedisCacheManager;
import com.booyaka.web.shiro.ShiroRealm;
import com.booyaka.web.system.service.SysMenuService;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfiguration {

	@Autowired
	SysMenuService menuService;

	/**
	 * 凭证匹配器
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1);
		return credentialsMatcher;
	}

	/**
	 * ShiroFilter
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		/* 默认的登陆访问url */
		shiroFilterFactoryBean.setLoginUrl("/system/login");
		/* 登陆成功访问url */
		shiroFilterFactoryBean.setSuccessUrl("/index");
		/* 未授权界面 */
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
		/* 自定义拦截器 */
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		shiroFilterFactoryBean.setFilters(filtersMap);
		/* 拦截器 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		/* 不需要认证 */
		filterChainDefinitionMap.put("/system/dologin", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/plugin/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		/* 退出 */
		filterChainDefinitionMap.put("/system/logout", "logout");
		/* 需要认证 */
//		filterChainDefinitionMap.put("/**/url", "requestURL");
//		for (SysMenu menu : ButtonList) {
//			filterChainDefinitionMap.put(menu.getResource(), "perms[" + menu.getMenuPath() + "]");
//		}
		// filterChainDefinitionMap.put("/menu/saveOrUpdate", "perms[a]");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
		return shiroFilterFactoryBean;
	}

	/**
	 * 安全管理器
	 */
	@Bean
	public DefaultWebSecurityManager defaultWebSecurityManager() {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(getShiroRealm());
		// defaultWebSecurityManager.setCacheManager(jedisCacheManager()); //使用redis缓存
		defaultWebSecurityManager.setCacheManager(memoryConstrainedCacheManager()); // 使用shiro自带缓存
		return defaultWebSecurityManager;
	}

	/**
	 * 自定义验证
	 */
	@Bean
	public ShiroRealm getShiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return shiroRealm;
	}

	/**
	 * Shiro方言
	 */
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * redis 缓存
	 */
	@Bean
	public ShiroJedisCacheManager jedisCacheManager() {
		return new ShiroJedisCacheManager();
	}

	/**
	 * shiro自带缓存
	 */
	@Bean
	public MemoryConstrainedCacheManager memoryConstrainedCacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@Bean
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");// 数据库异常处理
		mappings.setProperty("UnauthorizedException", "403");
		smer.setExceptionMappings(mappings); // None by default
		smer.setDefaultErrorView("error"); // No default
		smer.setExceptionAttribute("ex"); // Default is "exception"
		// r.setWarnLogCategory("example.MvcLogger"); // No default
		return smer;
	}
}
