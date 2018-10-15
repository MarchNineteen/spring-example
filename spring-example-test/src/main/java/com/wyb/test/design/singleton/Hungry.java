package com.wyb.test.design.singleton;

/**
 * @author Kunzite
 *
 * 饿汉式-线程安全
 */
public class Hungry {

    private static Hungry hungry = new Hungry();

    private Hungry(){
    }

    public static Hungry getInstance(){
        if (null == hungry) {
            hungry = new Hungry();
        }
        return hungry;
    }
}
