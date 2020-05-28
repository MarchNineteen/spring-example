package com.wyb.scanner.login.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:28
 */
public interface CacheService {

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    void putCache(String key, Object value);

    /**
     * 设置缓存，并设定缓存时长（秒）
     *
     * @param key
     * @param value
     * @param expire
     */
    void putCache(String key, Object value, int expire);

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    Object getCache(String key);

    /**
     * 获取缓存数据
     *
     * @param keys
     * @return
     */
    List<Object> getCaches(List<String> keys);

    /**
     * 删除缓存
     *
     * @param key
     */
    void removeCache(String key);

    /**
     * 队列缓存设置
     *
     * @param key
     * @param value
     */
    void putQueue(String key, Object value);


    /**
     * 获取队列缓存
     *
     * @param key
     * @return
     */
    Object getQueue(String key);

    /***************************************************
     ****** 队列操作
     ***************************************************/
    /**
     * 往队列中添加元素
     *
     * @param key
     * @param value
     */
    void qadd(String key, Object value);

    /**
     * 往队列头部压入元素
     *
     * @param key
     * @return
     */
    Object qpush(String key, Object value);

    /**
     * 获取并删除队列的首元素
     *
     * @param key
     * @return
     */
    Object qpop(String key);

    /**
     * 获取并删除队列的尾元素
     *
     * @param key
     * @return
     */
    Object qrpop(String key);

    /**
     * 获取指定下标的队列的元素
     *
     * @param key
     * @param index 如果index<0则表示从队尾开始获取
     * @return
     */
    Object qget(String key, long index);

    /**
     * queue range get <br/>
     * 获取指定区间的队列元素
     *
     * @param key
     * @param start 如果start<0则表示从队尾开始计数
     * @param end   如果end<0则表示从队尾开始计数
     * @return
     */
    List<Object> qrget(String key, long start, long end);

    /******************************
     * ******** 栈操作 ***********
     ******************************/
    void putStack(String key, Object value);

    Object getStack(String key);


    int length(String key);

    /**
     * 设置cache超时时间
     * @param key
     * @param timeout
     * @param unit
     */
    void expire(String key, long timeout, TimeUnit unit);

    /******************************
     ********* hash操作 ***********
     ******************************/
    /**
     * hash put all
     *
     * @param key
     * @param map
     */
    void hputs(String key, Map<? extends Object, ? extends Object> map);

    /**
     * hash put
     *
     * @param key
     * @param hashKey
     * @param value
     */
    void hput(String key, Object hashKey, Object value);

    /**
     * hash get
     *
     * @param key
     * @param hashKey
     * @return
     * @author Het.C
     * @date 2015年10月12日
     */
    Object hget(String key, Object hashKey);

    /**
     * hash get
     *
     * @param key
     * @return
     */
    Map<Object, Object> hgetAll(String key);

    /**
     * hash remove
     *
     * @param key
     * @param hashKey
     */
    void hrem(String key, Object hashKey);

    /**
     * hash size
     *
     * @param key
     * @return
     */
    long hsize(String key);

    /**
     * hash keys
     *
     * @param key
     * @return
     */
    Set<?> hkeys(String key);

    /**
     * hash keys
     *
     * @param key
     * @return
     */
    List<Object> hvals(String key);

    /**
     * 取出Map
     */
    Map<Object, Object> hMap(String key);

    /**
     * 查询该hashKey对应的field是否存在
     */
    boolean hexists(String key, Object hashKey);

    /******************************
     ********* set操作 ***********
     ******************************/
    /**
     * set add
     *
     * @param key
     * @param values
     */
    void sadd(String key, Object... values);


    /**
     * set exist
     *
     * @param key
     */
    boolean scontains(String key, Object item);

    /**
     * set length
     *
     * @param key
     */
    long ssize(String key);

    /**
     * set get
     *
     * @param key
     */
    Set<?> sget(String key);

    /**
     * set remove
     * @param key
     * @param values
     * @return
     */
    long sremove(String key, Object... values);

    /************************************************************
     ********************** zset 操作*****************************
     ************************************************************/
    /**
     * zset put
     * @param key
     * @param value
     * @param score
     */
    void zput(String key, Object value, double score);

    /**
     * zset score get
     * @param key
     * @param arg0
     * @param arg1
     * @return
     */
    Set<?> zsget(String key, Double arg0, Double arg1);

    /**
     * zset range get
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<?> zrget(String key, Long start, Long end);

    /**
     * zset remove
     * @param key
     * @param values
     * @return
     */
    long zremove(String key, Object... values);

    /**
     * zset size
     * @param key
     * @return
     */
    long zsize(String key);


    /**
     * 模糊查询
     */
    Set<String> fuzzyQuery(String pattern);

    void publish(String channel, Object object);

    /**
     * 根据key生成自增ID
     * @param key
     * @return
     */
    long generate(String key);
}
