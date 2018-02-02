package com.wyb.cache.service.impl;

import com.wyb.cache.service.CacheService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:53
 */
public class MemcachedServiceImpl implements CacheService {
    @Override
    public void putCache(String key, Object value) {

    }

    @Override
    public void putCache(String key, Object value, int expire) {

    }

    @Override
    public Object getCache(String key) {
        return null;
    }

    @Override
    public List<Object> getCaches(List<String> keys) {
        return null;
    }

    @Override
    public void removeCache(String key) {

    }

    @Override
    public void putQueue(String key, Object value) {

    }

    @Override
    public Object getQueue(String key) {
        return null;
    }

    @Override
    public void qadd(String key, Object value) {

    }

    @Override
    public Object qpush(String key, Object value) {
        return null;
    }

    @Override
    public Object qpop(String key) {
        return null;
    }

    @Override
    public Object qrpop(String key) {
        return null;
    }

    @Override
    public Object qget(String key, long index) {
        return null;
    }

    @Override
    public List<Object> qrget(String key, long start, long end) {
        return null;
    }

    @Override
    public void putStack(String key, Object value) {

    }

    @Override
    public Object getStack(String key) {
        return null;
    }

    @Override
    public int length(String key) {
        return 0;
    }

    @Override
    public void expire(String key, long timeout, TimeUnit unit) {

    }

    @Override
    public void hputs(String key, Map<?, ?> map) {

    }

    @Override
    public void hput(String key, Object hashKey, Object value) {

    }

    @Override
    public Object hget(String key, Object hashKey) {
        return null;
    }

    @Override
    public Map<Object, Object> hgetAll(String key) {
        return null;
    }

    @Override
    public void hrem(String key, Object hashKey) {

    }

    @Override
    public long hsize(String key) {
        return 0;
    }

    @Override
    public Set<?> hkeys(String key) {
        return null;
    }

    @Override
    public List<Object> hvals(String key) {
        return null;
    }

    @Override
    public Map<Object, Object> hMap(String key) {
        return null;
    }

    @Override
    public boolean hexists(String key, Object hashKey) {
        return false;
    }

    @Override
    public void sadd(String key, Object... values) {

    }

    @Override
    public boolean scontains(String key, Object item) {
        return false;
    }

    @Override
    public long ssize(String key) {
        return 0;
    }

    @Override
    public Set<?> sget(String key) {
        return null;
    }

    @Override
    public long sremove(String key, Object... values) {
        return 0;
    }

    @Override
    public void zput(String key, Object value, double score) {

    }

    @Override
    public Set<?> zsget(String key, Double arg0, Double arg1) {
        return null;
    }

    @Override
    public Set<?> zrget(String key, Long start, Long end) {
        return null;
    }

    @Override
    public long zremove(String key, Object... values) {
        return 0;
    }

    @Override
    public long zsize(String key) {
        return 0;
    }

    @Override
    public Set<String> fuzzyQuery(String pattern) {
        return null;
    }

    @Override
    public void lock(String key) {

    }

    @Override
    public void lock(String key, int lockTimeoutMS) {

    }

    @Override
    public boolean tryLock(String key, int timeoutMS) {
        return false;
    }

    @Override
    public boolean tryLock(String key, int timeoutMS, int lockTimeoutMS) {
        return false;
    }

    @Override
    public void unlock(String key) {

    }

    @Override
    public long generate(String key) {
        return 0;
    }

//    @Override
//    public String setValue(String key, String value) {
//        return null;
//    }
//
//    @Override
//    public String getValueByKey(String key) {
//        return null;
//    }
//
//    @Override
//    public boolean delValueBykey(String key) {
//        return false;
//    }
}
