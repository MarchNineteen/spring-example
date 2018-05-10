package com.wyb.test.thread.threadLocal;


/**
 * @author Kunzite
 * ThreadLocal的具体使用方法
 */
public class ThreadLocalDemo {

    //存放线程变量
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };//①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值

    //②获取下一个序列值
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    private static class TestClient extends Thread {

        private ThreadLocalDemo sn;

        public TestClient(ThreadLocalDemo sn) {
            this.sn = sn;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                //④每个线程打出3个序列值
                System.out.println("thread[" +
                        Thread.currentThread().getName() + "]sn[" +
                        sn.getNextNum() + "]");
            }
        }
    }

    private static class TestClient1 extends Thread {

        private ThreadLocalDemo sn;

        public TestClient1(ThreadLocalDemo sn) {
            this.sn = sn;
        }

        public void run() {
            for (int i = 0; i < 5; i++) {
                //④每个线程打出3个序列值
                System.out.println("thread[" +
                        Thread.currentThread().getName() + "]sn[" +
                        sn.getNextNum() + "]");
            }
        }
    }

    public static void main(String[] args) {
        ThreadLocalDemo sn = new ThreadLocalDemo();
        //③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient1 t2 = new TestClient1(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }
}
