package com.wyb.thread.base.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Marcher丶
 * <p>
 * 允许多个线程同时访问
 */
public class SemaphoreDemo {

    public static final int requestNum = 550;

    public static void main(String[] args) {
        // 线程池
        ExecutorService executor = Executors.newFixedThreadPool(300);
        // 一次允许执行的线程数
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < requestNum; i++) {
            final int currentRequestNum = i;
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    // 执行业务
                    test(currentRequestNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        System.out.println("finish");
    }

    public static void test(int currentRequestNum) throws InterruptedException {
        // 模拟请求的耗时操作
        Thread.sleep(1000);
        System.out.println("当前线程为" + currentRequestNum + "号线程");
        // 模拟请求的耗时操作
        Thread.sleep(1000);
    }
}
