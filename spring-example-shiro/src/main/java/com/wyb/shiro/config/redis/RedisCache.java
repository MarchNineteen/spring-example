package com.wyb.shiro.config.redis;

import com.wyb.shiro.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisCache<K, V> implements Cache<K, V> {

    private long expireTime = 120;// 缓存的超时时间，单位为s  
    private String name = "redis-cache";
    private RedisTemplate<String, V> redisTemplate;// 通过构造方法注入该对象
    // private RedisTemplate<K, V> redisTemplate;// 通过构造方法注入该对象

    public RedisCache() {
        super();
    }

    public RedisCache(long expireTime, RedisTemplate<String, V> redisTemplate, String name) {
        super();
        this.expireTime = expireTime;
        this.redisTemplate = redisTemplate;
        this.name = name;
    }

    /**
     * 通过key来获取对应的缓存对象
     * 通过源码我们可以发现，shiro需要的key的类型为Object，V的类型为AuthorizationInfo对象
     */
    @Override
    public V get(K key) throws CacheException {
        //return redisTemplate.opsForValue().get(key);
        log.info("将权限信息加入缓存中{}", new String(SerializationUtils.serialize(getCacheKey(key))));
        V obj = redisTemplate.opsForValue().get(name + new String(SerializationUtils.serialize(getCacheKey(key))));
        if (obj != null) {
            System.out.println(obj.toString());
        }
        return obj;
    }

    /**
     * 将权限信息加入缓存中
     */
    @Override
    public V put(K key, V value) throws CacheException {

        // redisTemplate.opsForValue().set(key, value, this.expireTime, TimeUnit.SECONDS);
        log.info("将权限信息加入缓存中{}", new String(SerializationUtils.serialize(getCacheKey(key))));
        redisTemplate.opsForValue().set(new String(SerializationUtils.serialize(getCacheKey(key))), value, this.expireTime, TimeUnit.SECONDS);

        return value;
    }

    /**
     * 将权限信息从缓存中删除
     */
    @Override
    public V remove(K key) throws CacheException {
        //  V v = redisTemplate.opsForValue().get(key);
        // redisTemplate.opsForValue().getOperations().delete(key);

        V v = redisTemplate.opsForValue().get(name + new String(SerializationUtils.serialize(getCacheKey(key))));
        redisTemplate.opsForValue().getOperations().delete(name + new String(SerializationUtils.serialize(getCacheKey(key))));
        log.info("从redis中删除key为{}的权限缓存信息", key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("clearclearclearclearclearclearclearclearclear");
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private String getCacheKey(Object key) {
        return Constants.REDIS_SHIRO_CACHE + key;
    }

}