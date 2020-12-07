package com.booyaka.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.booyaka.web.thymeleaf.DbDialect;

/**
 * Thymeleaf 下拉标签 https://github.com/jeesun/thymeleaf-extras-db
 */
@Configuration
public class ThymeleafConfig {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Bean
	public DbDialect dbDialect() {
		return new DbDialect(jdbcTemplate);
	}

}
