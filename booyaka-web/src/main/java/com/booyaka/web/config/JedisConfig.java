package com.booyaka.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.booyaka.emuns.RedisEnum;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Component
public class JedisConfig {

	@Value("${spring.redis.host}")
	private static String host;

	@Value("${spring.redis.port}")
	private static Integer port;

	@Value("${spring.redis.password}")
	private static String password;

	@Value("${spring.redis.timeout}")
	private static Integer timeout;

	public static Jedis getJedis(RedisEnum index) {
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password, index.getIndex());
		return jedisPool.getResource();
	}

}
