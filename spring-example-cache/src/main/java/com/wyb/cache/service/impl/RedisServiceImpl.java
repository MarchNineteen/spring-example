package com.wyb.cache.service.impl;

import com.wyb.cache.constant.CacheKeyContant;
import com.wyb.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:26
 */
@Slf4j
@Service("redisService")
public class RedisServiceImpl implements CacheService {

//    @Resource
//    JedisCluster jedisCluster;
//
//    @Override
//    public String setValue(String key, String value) {
//        return jedisCluster.set(key,value);
//    }
//
//    @Override
//    public String getValueByKey(String key) {
//        return jedisCluster.get(key);
//    }
//
//    @Override
//    public boolean delValueBykey(String key) {
//        Long flag = jedisCluster.del(key);
//        if (flag>0){
//            return true;
//        }
//        return false;
//    }

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void putCache(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(new String(key.getBytes("utf-8")), value);
            log.info("添加缓存成功");
        } catch (Exception e) {
            log.error("PUT cache exception [key=" + key + ", value=" + value + "].", e);
        }
    }

    @Override
    public void putCache(String key, Object value, int expire) {
        try {
            redisTemplate.opsForValue().set(new String(key.getBytes("utf-8")), value, expire, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("PUT cache exception [key=" + key + ", value=" + value + ", expire=" + expire + "].", e);
        }
    }

    @Override
    public Object getCache(String key) {
        try {
            return redisTemplate.opsForValue().get(new String(key.getBytes("utf-8")));
        } catch (Exception e) {
            log.error("GET cache exception [key=" + key + "].", e);
        }
        return null;
    }

    @Override
    public List<Object> getCaches(List<String> keys) {
        try {
            return redisTemplate.opsForValue().multiGet(keys);
        } catch (Throwable e) {
            log.error("sMget cache exception [key=" + keys + "].", e);
            return null;
        }
    }

    @Override
    public void removeCache(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("Remove cache exception [key=" + key + "].", e);
        }
    }

    @Override
    public void putQueue(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            log.error("PUT Queue cache exception [key=" + key + ", value=" + value + "].", e);
        }
    }

    @Override
    public Object getQueue(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("GET Queue cache exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public void qadd(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            log.error("Add queue cache exception [key=" + key + ", value=" + value + "].", e);
        }
    }

    @Override
    public Object qpush(String key, Object value) {
        try {
            return redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            log.error("Pop queue cache exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public Object qpop(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("Pop queue cache exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public Object qrpop(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("Pop queue cache exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public Object qget(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("Get queue cache exception [key=" + key + ", index=" + index + "].", e);
            return null;
        }
    }

    @Override
    public List<Object> qrget(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("Range Queue cache exception [key=" + key + ", start=" + start + ", end = " + end + "].", e);
            return null;
        }
    }

    @Override
    public void putStack(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            log.error("PUT Stack cache exception [key=" + key + ", value=" + value + "].", e);
        }
    }

    @Override
    public Object getStack(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error("GET Stack cache exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public int length(String key) {
        try {
            return redisTemplate.opsForList().size(key).intValue();
        } catch (Exception e) {
            log.error("GET cache length exception [key=" + key + "].", e);
            return 0;
        }
    }

    @Override
    public void expire(String key, long timeout, TimeUnit unit) {
        try {
            redisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            log.error("SET expire time exception [key=" + key + "].", e);
        }
    }

    @Override
    public void hputs(String key, Map<?, ?> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
        } catch (Exception e) {
            log.error("PUT All Hash exception [key=" + key + "].", e);
        }
    }

    @Override
    public void hput(String key, Object hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
        } catch (Exception e) {
            log.error("PUT Hash exception [key=" + key + "].", e);
        }
    }

    @Override
    public Object hget(String key, Object hashKey) {
        try {
            return redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            log.error("GET Hash exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public Map<Object, Object> hgetAll(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error("GET Hash exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public void hrem(String key, Object hashKey) {
        try {
            redisTemplate.opsForHash().delete(key, hashKey);
        } catch (Exception e) {
            log.error("DELETE Hash exception [key=" + key + "].", e);
        }
    }

    @Override
    public long hsize(String key) {
        try {
            return redisTemplate.opsForHash().size(key);
        } catch (Exception e) {
            log.error("GET Hash size exception [key=" + key + "].", e);
            return 0;
        }
    }

    @Override
    public Set<?> hkeys(String key) {
        try {
            return redisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            log.error("GET Hash size exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public List<Object> hvals(String key) {
        try {
            return redisTemplate.opsForHash().values(key);
        } catch (Exception e) {
            log.error("GET Hash size exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public Map<Object, Object> hMap(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error("GET Map size exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public boolean hexists(String key, Object hashKey) {
        try {
            return redisTemplate.opsForHash().hasKey(key, hashKey);
        } catch (Exception e) {
            log.error("GET Map size exception [key=" + key + "].", e);
            return false;
        }
    }

    @Override
    public void sadd(String key, Object... values) {
        try {
            redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("Add set exception [key=" + key + "].", e);
        }
    }

    @Override
    public boolean scontains(String key, Object item) {
        try {
            return redisTemplate.opsForSet().isMember(key, item);
        } catch (Exception e) {
            log.error("Get set size exception [key=" + key + "].", e);
            return false;
        }
    }

    @Override
    public long ssize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("Get set size exception [key=" + key + "].", e);
            return 0;
        }
    }

    @Override
    public Set<?> sget(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("Get set exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public long sremove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Throwable e) {
            log.error("Get set exception [key=" + key + "].", e);
            return 0;
        }
    }

    @Override
    public void zput(String key, Object value, double score) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            log.error("PUT Zset exception [key=" + key + "].", e);
        }
    }

    @Override
    public Set<?> zsget(String key, Double arg0, Double arg1) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, arg0, arg1);
        } catch (Exception e) {
            log.error("GET Zset exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public Set<?> zrget(String key, Long start, Long end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            log.error("GET Zset exception [key=" + key + "].", e);
            return null;
        }
    }

    @Override
    public long zremove(String key, Object... values) {
        try {
            return redisTemplate.opsForZSet().remove(key, values);
        } catch (Exception e) {
            log.error("GET Zset exception [key=" + key + "].", e);
            return 0;
        }
    }

    @Override
    public long zsize(String key) {
        try {
            return redisTemplate.opsForZSet().size(key);
        } catch (Exception e) {
            log.error("GET zsize exception [key=" + key + "].", e);
            return 0;
        }
    }

    @Override
    public Set<String> fuzzyQuery(String pattern) {
        try {
            return redisTemplate.keys(pattern);
        } catch (Exception e) {
            log.error("GET fuzzyQuery exception [key=" + pattern + "].", e);
            return null;
        }
    }

    @Override
    public void publish(String channel, Object object) {
        try {
            redisTemplate.convertAndSend(channel, object);
        } catch (Exception e) {
            log.error("publish  exception [channel=" + channel + "].", e);
        }
    }

    @Override
    public void lock(String key) {
        lock(key, 10000);
    }

    @Override
    public void lock(String key, int lockTimeoutMS) {
        tryLock(key, -1, lockTimeoutMS);
    }

    @Override
    public boolean tryLock(String key, int timeoutMS) {
        return tryLock(key, timeoutMS, 10000);
    }

    @Override
    public boolean tryLock(String key, int timeoutMS, int lockTimeoutMS) {
        if (key == null) {
            throw new IllegalArgumentException("the cache key to be locked can not be null");
        }
        long t = System.currentTimeMillis();
        String _lockKey = String.format("%s%s", CacheKeyContant.DS_LOCK, key);
        while (true) {
            if (redisTemplate.opsForValue().setIfAbsent(_lockKey, t + lockTimeoutMS + 1)) {
                redisTemplate.expire(_lockKey, lockTimeoutMS, TimeUnit.MILLISECONDS);
                return true;
            }
            // 获取锁超时判断
            if (timeoutMS >= 0) {
                if (System.currentTimeMillis() - t >= timeoutMS) {
                    return false;
                }
            } else {
                // 判断锁是否超时，用于处理expire没有执行即kill进行的情况造成的死锁
                Object expireAt = redisTemplate.opsForValue().get(_lockKey);
                if (expireAt instanceof Long) { // 兼容
                    if ((Long) expireAt <= System.currentTimeMillis()) {
                        redisTemplate.delete(_lockKey);
                    }
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void unlock(String key) {
        String _lockKey = String.format("%s%s", CacheKeyContant.DS_LOCK, key);
        removeCache(_lockKey);
    }

    @Override
    public long generate(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }
}
