package com.booyaka.web.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.booyaka.emuns.RedisEnum;
import com.booyaka.web.config.JedisConfig;

import redis.clients.jedis.Jedis;

public class ShiroJedisCache<K, V> implements Cache<K, V>, Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroJedisCache.class);

	private static final String PREFIX = "SHIRO_SESSION_ID";

	private byte[] getByteKey(K k) {
		if (k instanceof String) {
			String key = PREFIX + k;
			return key.getBytes();
		} else {
			return SerializationUtils.serialize((Serializable) k);
		}
	}

	@Override
	public int size() {
		Long size = JedisConfig.getJedis(RedisEnum.SHIRO).dbSize();
		return size.intValue();
	}

	@Override
	public Set<K> keys() {
		Jedis jedis = JedisConfig.getJedis(RedisEnum.SHIRO);
		Set<byte[]> bytes = jedis.keys((PREFIX + new String("*")).getBytes());
		Set<K> keys = new HashSet<>();
		if (bytes != null) {
			for (byte[] b : bytes) {
				keys.add(SerializationUtils.deserialize(b));
			}
		}
		jedis.close();
		return keys;
	}

	@Override
	public Collection<V> values() {
		Jedis jedis = JedisConfig.getJedis(RedisEnum.SHIRO);
		Set<K> keys = this.keys();
		List<V> lists = new ArrayList<>();
		for (K k : keys) {
			byte[] bytes = jedis.get(getByteKey(k));
			lists.add(SerializationUtils.deserialize(bytes));
		}
		jedis.close();
		return lists;
	}

	@Override
	public void clear() {
		JedisConfig.getJedis(RedisEnum.SHIRO).flushDB();
	}

	@Override
	public V put(K k, V v) {
		Jedis jedis = JedisConfig.getJedis(RedisEnum.SHIRO);
		LOGGER.info("key---->" + k + "     value---->" + v.toString());
		jedis.set(getByteKey(k), SerializationUtils.serialize((Serializable) v));
		jedis.expire(getByteKey(k), 10000);
		byte[] bytes = jedis.get(SerializationUtils.serialize(getByteKey(k)));
		jedis.close();
		if (bytes == null) {
			return null;
		}
		return SerializationUtils.deserialize(bytes);
	}

	@Override
	public V get(K k) {
		Jedis jedis = JedisConfig.getJedis(RedisEnum.SHIRO);
		if (k == null) {
			return null;
		}
		byte[] bytes = jedis.get(getByteKey(k));
		jedis.close();
		if (bytes == null) {
			return null;
		}
		V v = SerializationUtils.deserialize(bytes);
		LOGGER.info("key---->" + k + "     value---->" + v.toString());
		return v;
	}

	@Override
	public V remove(K k) {
		Jedis jedis = JedisConfig.getJedis(RedisEnum.SHIRO);
		byte[] bytes = jedis.get(getByteKey(k));
		jedis.del(getByteKey(k));
		jedis.close();
		if (bytes == null) {
			return null;
		}
		return SerializationUtils.deserialize(bytes);
	}

}
