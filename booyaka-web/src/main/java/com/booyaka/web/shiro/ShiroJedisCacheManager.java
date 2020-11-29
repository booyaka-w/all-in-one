package com.booyaka.web.shiro;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

@SuppressWarnings("unchecked")
public class ShiroJedisCacheManager implements CacheManager {

	@SuppressWarnings("rawtypes")
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

	@Override
	public <K, V> Cache<K, V> getCache(String s) throws CacheException {
		Cache<K, V> cache = caches.get(s);
		if (cache == null) {
			cache = new ShiroJedisCache<K, V>();
			caches.put(s, cache);
		}
		return cache;
	}
}
