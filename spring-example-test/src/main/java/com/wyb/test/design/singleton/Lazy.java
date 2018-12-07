package com.wyb.test.design.singleton;

/**
 * @author Kunzite
 * <p>
 * 懒汉式-线程不安全
 */
public class Lazy {

    private static Lazy lazy;

    private Lazy() {
    }

    public Lazy getInstance() {
        if (null != lazy) {
            lazy = new Lazy();
        }
        return lazy;
    }
}

/*
* 懒汉式-线程安全 对方法加锁 影响了性能
 */
class LazySecurity {

    private static LazySecurity lazySecurity;

    private LazySecurity() {
    }

    public static synchronized LazySecurity getUniqueInstance() {
        if (lazySecurity == null) {
            lazySecurity = new LazySecurity();
        }
        return lazySecurity;
    }
}
