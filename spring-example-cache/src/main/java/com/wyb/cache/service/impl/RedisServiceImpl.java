package com.wyb.cache.service.impl;

import com.wyb.cache.service.CacheService;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:26
 */
public class RedisServiceImpl implements CacheService {

    @Resource
    JedisCluster jedisCluster;

    @Override
    public String setValue(String key, String value) {
        return jedisCluster.set(key,value);
    }

    @Override
    public String getValueByKey(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public boolean delValueBykey(String key) {
        Long flag = jedisCluster.del(key);
        if (flag>0){
            return true;
        }
        return false;
    }
}
