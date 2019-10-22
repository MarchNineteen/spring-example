package com.wyb.thread.apply;

/**
 * 电影院卖票问题  100 张票 2个窗口
 *
 * @author Marcher丶
 */
public class TicketOpen implements Runnable {
    private static int total = 100;// 100张票

    @Override
    public void run() {
        while (total > 0) {
            total -= 1;
            System.out.println("当前" + Thread.currentThread().getName() + ", 卖出一张票，还剩" + (total) +"张");
        }
    }

    public static void main(String[] args) {
        int open = 2; //2 2个窗口即2个线程
        for (int i = 0; i < open; i++) {
            Thread thread = new Thread(new TicketOpen());
            thread.setName("窗口" + i);
            thread.start();
        }
    }
}
