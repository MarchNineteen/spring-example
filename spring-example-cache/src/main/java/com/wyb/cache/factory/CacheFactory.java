package com.wyb.cache.factory;

import com.wyb.cache.constant.CacheType;
import com.wyb.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:40
 */
@Component
//@DependsOn({"redisService", "memcacheService"})
public class CacheFactory {

    @Qualifier(value = "redisService")
    @Autowired
    private CacheService redisService;

    @Autowired
    @Qualifier(value = "memcacheService")
    private CacheService memcacheService;

    public CacheService getCache(CacheType type) {
        switch (type) {
            case REDIS:
                return redisService;
            case MEMCACHE:
                return memcacheService;
            default:
                return redisService;
        }
    }
}
