package com.wyb.test.singleton;

/**
 * <p>
 * 单例模式的第二种实现
 * 这种写法和第一种的区别在于：实例并没有直接实例化，而是在静态工厂方法被调用的时候才进行的，而且对静态工厂方法使用了同步化，以处理多线程并发的环境。
 * 这两种写法还有两个非常有意思的名字：第一种称为饿汉式单例，第二种称为懒汉式单例。至于为什么起这个名字，自己好好想想吧。
 * 饿汉式单例在自己被加载时就将自己实例化，如果从资源利用效率角度来讲，比懒汉式单例类稍差些。但是从速度和反应时间角度来讲，则比懒汉式要稍好些。
 *
 * @author Kunzite
 */
public class Singleton2 {

    private static final Singleton2 instance = null;

    public Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        if (null == instance) {
            return new Singleton2();
        }
        return instance;
    }
}
