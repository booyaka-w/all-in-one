package com.booyaka.web.thymeleaf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSON;

/**
 * 简单的字典
 **/

public class SelectOptionServiceImpl implements SelectOptionService{
	
	private Logger log = LoggerFactory.getLogger(SelectOptionServiceImpl.class);
	
	private CacheManager cacheManager;

	public SelectOptionServiceImpl() {
	}

	public SelectOptionServiceImpl(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	@Override
	public List<Option> queryOption(String dictName, JdbcTemplate jdbcTemplate, boolean cacheable) {
		Cache cache = null;
		if (null != cacheManager) {
			cache = cacheManager.getCache("optionListCache");
		}
		if (null != cache && null != cache.get(dictName) && cacheable) {
			log.info("read from optionListCache");
			return JSON.parseArray(String.valueOf(cache.get(dictName).get()), Option.class);
		}

		List<Option> optionList = new ArrayList<>();
		List<Map<String, Object>> results = new ArrayList<>();
		String sql = "SELECT * FROM `configure` WHERE tag_name=? order by tag_sort asc";
		log.info(sql);
		results = jdbcTemplate.queryForList(sql, new Object[] { dictName });
		for (int i = 0; i < results.size(); i++) {
			Option option = new Option();
			option.setValue(String.valueOf(results.get(i).get("tag_value")));
			option.setText(String.valueOf(results.get(i).get("tag_text")));
			optionList.add(option);
		}
		log.info(JSON.toJSONString(optionList));
		if (null != cache && cacheable) {
			cache.put(dictName, JSON.toJSONString(optionList));
		}
		return optionList;
	}

}
