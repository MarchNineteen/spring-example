package com.wyb.test.thread.threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kunzite
 * ThreadLocal 处理高并发状态下的线程安全问题
 * <p>
 * ThreadLocal实现 设计思路：
 * 在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本
 */
public class SimpleThreadLocal {

    private static Map map = Collections.synchronizedMap(new HashMap());  //注意 SynchronizedMap 和  ConcurrentHashMap

    // 变量初始化
    public Object initialValue() {
        return null;
    }

    public Object get() {
        Thread currentThread = Thread.currentThread();
        Object o = map.get(currentThread);
        if (null == o && !map.containsKey(currentThread)) {
            o = initialValue();
            map.put(currentThread, o);
        }
        return o;
    }

    public void remove() {
        map.remove(Thread.currentThread());
    }
}
