package com.booyaka.web.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

/**
 * 自定义标签
 **/

public class DbDialect extends AbstractProcessorDialect {

	/**
	 * 定义方言名称
	 */
	private static final String DIALECT_NAME = "Database Dialect";

	private JdbcTemplate jdbcTemplate;

	public DbDialect(JdbcTemplate jdbcTemplate) {
		super(DIALECT_NAME, "t", 1000);
		this.jdbcTemplate = jdbcTemplate;
	}

	public DbDialect(JdbcTemplate jdbcTemplate, CacheManager cacheManager) {
		super(DIALECT_NAME, "t", 1000);
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * Initialize the dialect's processors.
	 *
	 * Note the dialect prefix is passed here because, although we set "hello" to be
	 * the dialect's prefix at the constructor, that only works as a default, and at
	 * engine configuration time the user might have chosen a different prefix to be
	 * used.
	 */
	@Override
	public Set<IProcessor> getProcessors(final String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new SelectElementTag(dialectPrefix, jdbcTemplate));
		return processors;
	}
}
