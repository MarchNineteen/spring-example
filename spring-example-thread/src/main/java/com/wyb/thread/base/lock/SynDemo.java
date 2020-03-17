package com.wyb.thread.base.lock;

/**
 * @author Marcher丶
 * 使用syc 必须考虑锁的对象
 */
public class SynDemo {

    public static void main(String[] args) {
        Function function = new Function();
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 50; i++) {
                function.successorFun(i);
            }
        });
        t1.start();

        for (int i = 1; i <= 50; i++) {
            function.mainFun(i);
        }

    }

    static class Function {
        private boolean flag = true;

        public synchronized void successorFun(int loop) {
            while (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("子线程第" + loop + "次循环的第" + i + "号");
            }
            flag = false;
            this.notify();
        }

        public synchronized void mainFun(int loop) {
            // 先执行子线程
            while (flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 20; i++) {
                System.out.println("主线程第" + loop + "次循环的第" + i + "号");
            }
            flag = true;
            this.notify();
        }

    }

}
