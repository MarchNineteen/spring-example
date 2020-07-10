package com.wyb.thread.base.countDownLatch;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Marcher丶
 * 模拟一群朋友相约聚餐，大家都到了就开始吃饭，大家都吃完饭了就去结账，并且约定谁最后到达饭店，谁就买单。
 *
 * https://blog.csdn.net/qq_22008739/article/details/107201086?tdsourcetag=s_pctim_aiomsg
 */
public class CountDownLatchAndCyclicBarrierDemo {

    private static List<String> persons = Arrays.asList("张三", "李四", "王五", "赵六", "陈七");

    public static void main(String[] args) {
        System.out.println(persons.toString() + "相约聚餐");
        Thread[] threads = new Thread[persons.size()];
        AtomicReference<String> payUser = new AtomicReference<>("");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(persons.size(), () -> {
            payUser.set(Thread.currentThread().getName());
            System.out.println("大家都到了，准备去吃饭吧，根据约定：【" + Thread.currentThread().getName() + "】你最后到达，今天的消费由你买单");

            CountDownLatch countDownLatch = new CountDownLatch(persons.size());
            for (int i = 0; i < persons.size(); i++) {
                String name = persons.get(i);
                threads[i] = new Thread(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(20));
                        System.out.println(name + "吃完饭了，等待其他人");
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, name);
                threads[i].start();
            }
            try {
                countDownLatch.await();
                System.out.println("所有人都用餐完毕，根据约定【" + payUser + "】去结账");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        /**
         * 使用CyclicBarrier来处理等所有线程都就位了再统一一起开始下一件事情
         */
        for (int i = 0; i < persons.size(); i++) {
            String name = persons.get(i);
            threads[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println(name + "已到达饭店，到达时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, name);
            threads[i].start();
        }
    }
}
