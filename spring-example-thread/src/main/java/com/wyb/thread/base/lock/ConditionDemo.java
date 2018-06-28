package com.wyb.thread.base.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kunzite
 */
public class ConditionDemo {

    public static void main(String[] args) {
        final BusinessLock business = new BusinessLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    business.sub(i);
                }
            }
        }).start();

//        new Thread( () -> {
//            for (int i = 1; i <= 50; i++){
//                business.sub(i);
//            }
//        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }

    static class BusinessLock {
        private boolean bShouldSub = true;

        ReentrantLock lock = new ReentrantLock();
        java.util.concurrent.locks.Condition condition = lock.newCondition();

        public void sub(int i) {
            lock.lock();
            try {
                while (!bShouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (bShouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 100; j++) {
                    System.out.println("main thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

