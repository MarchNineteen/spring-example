package com.wyb.thread.base.synchronize;


/**
 * @author Kunzite
 * <p>
 *  同步普通方法，锁的是当前对象。对象锁，对同一个对象实例访问同步，多个对象实例访问不同步
 *  同步静态方法，锁的是当前 Class 对象。即类锁，对多个对象访问，同步
 *  同步块，锁的是 () 中的对象。
 * </p>
 */
public class Syn implements Runnable {

    Object lock;

    @Override
    public void run() {
//        synchronized单独使用：
//        代码块：如下，在多线程环境下，synchronized块中的方法获取了lock实例的monitor，如果实例相同，那么只有一个线程能执行该块内容
        synchronized (lock) {
            //todo something
        }
    }

    class Syn2 implements Runnable {
        //        直接用于方法： 相当于上面代码中用lock来锁定的效果，实际获取的是Thread1类的monitor。更进一步，如果修饰的是static方法，则锁定该类所有实例。
        @Override
        public synchronized void run() {
            //todo something
        }
    }

    class mt implements Runnable{

        @Override
        public void run() {
            int A[][] = new int[3][4];
        }
    }
}




