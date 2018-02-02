package com.wyb.cache.factory;

import com.wyb.cache.service.CacheService;
import com.wyb.cache.utils.SpringContextHolder;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:40
 */
public class CacheFactory {

    public static CacheService getCache(String type) {
        if (type.equals("REDIS")){
            return SpringContextHolder.getBean("redisServiceImpl");
        }
        else {
            return SpringContextHolder.getBean("");
        }
    }
}
