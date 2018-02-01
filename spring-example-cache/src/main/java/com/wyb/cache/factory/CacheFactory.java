package com.wyb.cache.factory;

import com.wyb.cache.service.impl.MemcachedServiceImpl;
import com.wyb.cache.service.impl.RedisServiceImpl;

/**
 * Description:
 *
 * @author: wangyingbo
 * @version: 2018-02-01 15:40
 */
public class CacheFactory {

    public static Cache getCache(String type) {
        if (type.equals("REDIS")){
            return new RedisServiceImpl();
        }
        else {
            return new MemcachedServiceImpl();
        }
    }
}
