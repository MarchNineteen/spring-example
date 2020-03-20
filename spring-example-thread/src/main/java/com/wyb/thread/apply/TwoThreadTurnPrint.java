package com.wyb.thread.apply;

/**
 * @author Marcher丶
 * 两个线程轮流打印数字，一直到100
 */
public class TwoThreadTurnPrint {
    private boolean flag = true;
    private int count = 0;

    public static void main(String[] args) {

        TwoThreadTurnPrint print = new TwoThreadTurnPrint();
        new Thread(() -> {
            print.print1();
        }).start();

        new Thread(() -> {
            print.print2();
        }).start();

    }


    public synchronized void print1() {
        for (int i = 0; i < 50; i++) {
            while (!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = !flag;
            System.out.println(Thread.currentThread().getName() + "_" + (++count));
            this.notify();
        }

    }

    public synchronized void print2() {
        for (int i = 0; i < 50; i++) {
            while (flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = !flag;
            System.out.println(Thread.currentThread().getName() + "_" + (++count));
            this.notify();
        }
    }
}
