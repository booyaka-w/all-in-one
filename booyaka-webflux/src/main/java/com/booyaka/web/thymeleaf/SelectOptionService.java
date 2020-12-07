package com.booyaka.web.thymeleaf;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 简单的字典
 **/

public interface SelectOptionService {

	public List<Option> queryOption(String dictName, JdbcTemplate jdbcTemplate, boolean cacheable);

}
