package com.wyb.thread.base.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marcher丶
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 * <p>
 * 把线程和功能拆分  抽象一个执行功能class
 * 定义一个变量轮到谁执行和谁先执行
 */
public class ConditionDemo {


    public static void main(String[] args) {
        Function function = new Function();
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                function.successorFun(i);
            }
        });
        t1.start();

        for (int i = 1; i <= 50; i++) {
            function.mianFun(i);
        }

    }


    static class Function {

        private boolean flag = true;// 子线程是否执行标识
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        /**
         * 子线程功能
         */
        public void successorFun(int loop) {
            lock.lock();
            try {
                // flag = false 子线程不能执行 执行阻塞等待主线程signal
                while (!flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i <= 10; i++) {
                    System.out.println("子线程第" + loop + "次循环的第" + i + "号");
                }
                // 执行完之后 标识改变 唤醒主线程
                flag = false;
                condition.signal();
            } finally {
                lock.unlock();
            }

        }


        /**
         * 主线程功能
         */
        public void mianFun(int loop) {

            lock.lock();
            try {
                // 先执行子线程
                while (flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i <= 20; i++) {
                    System.out.println("主线程第" + loop + "次循环的第" + i + "号");
                }
                flag = true;
                condition.signal();
            } finally {
                lock.unlock();
            }

        }
    }
}

