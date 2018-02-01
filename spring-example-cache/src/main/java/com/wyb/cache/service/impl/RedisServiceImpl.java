package com.wyb.cache.service.impl;

import com.wyb.cache.service.RedisService;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author: wangyingbo
 * @version: 2018-02-01 15:26
 */
public class RedisServiceImpl implements RedisService {

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
