package com.booyaka.oauth2.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	TokenStore tokenStore;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	ClientDetailsService clientDetailsService;

	/**
	 * 客户端详情
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	/**
	 * 安全约束
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
	}

	/**
	 * 令牌端点与令牌服务
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.authorizationCodeServices(authorizationCodeServices());
		endpoints.tokenStore(tokenStore);
		endpoints.tokenServices(tokenService());
		endpoints.userDetailsService(userDetailsService);
	}

	@Bean
	public AuthorizationServerTokenServices tokenService() {
		DefaultTokenServices service = new DefaultTokenServices();
		service.setClientDetailsService(clientDetailsService);
		service.setSupportRefreshToken(true);
		service.setTokenStore(tokenStore);
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
		service.setTokenEnhancer(tokenEnhancerChain);
		// 令牌默认有效期2小时
		service.setAccessTokenValiditySeconds(7200);
		// 刷新令牌默认有效期3天
		service.setRefreshTokenValiditySeconds(259200);
		return service;
	}

	@Bean
	public ClientDetailsService getClientDetailsService() {
		JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
		return jdbcClientDetailsService;
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices = new JdbcAuthorizationCodeServices(dataSource);
		return jdbcAuthorizationCodeServices;
	}
}
