package com.wyb.shiro.config.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author Kunizte
 */
public class RedisUtil<T> {

	private RedisTemplate<String, T> template;

	public RedisTemplate<String, T> getTemplate() {
		return template;
	}

	public void setTemplate(RedisTemplate<String, T> template) {
		this.template = template;
	}

	public <T> void set(String key, T value) {
		ValueOperations<String, T> vopt = (ValueOperations<String, T>) template.opsForValue();
		vopt.set(key, value);
	}

	public <T> void set(String key, T value, long time) {
		ValueOperations<String, T> vopt = (ValueOperations<String, T>) template.opsForValue();
		vopt.set(key, value, time, TimeUnit.SECONDS);
	}

	public <T> T get(String key) {
		ValueOperations<String, T> vopt = (ValueOperations<String, T>) template.opsForValue();
		return vopt.get(key);
	}

	public <T> void del(String key) {
		template.delete(key);
	}

	public <T> boolean hasKey(String key) {
		return template.hasKey(key);
	}

	public <T> void hset(String key, String field, T value) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		hopt.put(key, field, value);
	}

	public <T> T hmget(String key, String field) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		return hopt.get(key, field);
	}

	public <T> void hdel(String key, String field) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		hopt.delete(key, field, field);
	}

	public <T> boolean hexists(String key, String field) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		return hopt.hasKey(key, field);
	}
}
