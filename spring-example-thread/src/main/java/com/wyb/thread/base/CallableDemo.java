package com.wyb.thread.base;

import java.util.concurrent.*;

/**
 * @author Kunzite
 * 我们可以简单认为：Callable就是Runnable的扩展。
 * Runnable没有返回值，不能抛出受检查的异常，而Callable可以！
 *
 * Future一般我们认为是Callable的返回值，但他其实代表的是任务的生命周期(当然了，它是能获取得到Callable的返回值的)
 */
public class CallableDemo implements Callable<Integer>{

    private int number;

    public CallableDemo(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int x = 1; x <= number; x++) {
            sum += x;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        Callable callable1= new CallableDemo(100);
        Callable callable2= new CallableDemo(200);
        Future<Integer> future1 = pool.submit(callable1);
        Future<Integer> future2 = pool.submit(callable2);
        System.out.println(future1.get());
        System.out.println(future2.get());
        pool.shutdown();
    }
}
