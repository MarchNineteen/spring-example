package com.wyb.shiro.config.redis;

import com.wyb.shiro.config.properties.ShiroProperties;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
* @desc 该类提供redis cache。缓存 登录失败次数和用户权限。
 * 过期时间分别对应application.yml 中的
 * retry-expire-time-redis
*  authorization-expire-time-redis
* @author wangxinwei
* @date 2018-02-07 04:23
*/
public class ShiroRedisCacheManager implements CacheManager, Destroyable {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    private RedisTemplate redisTemplate;
    private long expireTime;

    @Autowired
    private ShiroProperties properties;

    // 注入Spring的缓存管理器
    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    public ShiroRedisCacheManager(RedisTemplate redisTemplateTemp){
    	redisTemplate = redisTemplateTemp;
    }
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = caches.get(name);
        if (null == cache) {
            if(name.equals("passwordRetryCache")){
                expireTime = properties.getRetryExpireTimeRedis();
            }else{
                expireTime = properties.getAuthorizationExpireTimeRedis();
            }
//            org.springframework.cache.Cache springCache = cacheManager.getCache(name);
            cache = new ShiroRedisCache<K, V>(expireTime, redisTemplate);// 为了简化代码的编写，此处直接new一个Cache
        }
        return cache;
    }

    @Override
    public void destroy() throws Exception {

    }

}
