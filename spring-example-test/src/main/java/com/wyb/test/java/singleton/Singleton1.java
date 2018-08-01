package com.wyb.test.java.singleton;

/**
 * @author Kunzite
 * <p>
 * 简单的单例模式
 * <p>
 * 在这个类被加载时，静态变量instance会被初始化，此时该类的私有构造函数被调用，这时候，单例类的唯一实例就被创建出来了
 * 值得注意的是：由于构造函数是私有的，因此该类不能被继承
 */
public class Singleton1 {

    private static final Singleton1 instance = new Singleton1();

    public Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }

}
