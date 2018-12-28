package com.wyb.test.design.create.singleton;

/**
 * @author Kunzite
 */
public class DoubleLock {

    // volatile 保证顺序性
    private volatile static DoubleLock singleton;

    private DoubleLock() {
    }

    public static DoubleLock getInstance() {
        if (null == singleton) {
            synchronized (DoubleLock.class) {
                if (null == singleton) {
                    singleton = new DoubleLock();
                }
            }
        }
        return singleton;
    }
}

