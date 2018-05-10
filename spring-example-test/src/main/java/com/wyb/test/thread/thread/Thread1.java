package com.wyb.test.thread.thread;


/**
 * @author Kunzite
 */
public class Thread1 implements Runnable {


    Object lock;
    public void run() {
//        synchronized单独使用：
//        代码块：如下，在多线程环境下，synchronized块中的方法获取了lock实例的monitor，如果实例相同，那么只有一个线程能执行该块内容
        synchronized (lock) {
            //todo something
        }
    }
}

class Thread2 implements Runnable {
//        直接用于方法： 相当于上面代码中用lock来锁定的效果，实际获取的是Thread1类的monitor。更进一步，如果修饰的是static方法，则锁定该类所有实例。
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
