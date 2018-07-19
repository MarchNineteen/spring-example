package com.wyb.thread.pool.customizePool;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Kunzite
 * @Description: 测试多线程池类
 */
@Slf4j
public class TestMain {

    public static void main(String[] args) {
        log.info("start....");
        //初始化10个线程，默认5个初始化线程
        Pool<PoolThread> pool = new ThreadPool(10);
        //初始化100个任务
        Task[] task = new Task[100];
        for (int i = 0; i < task.length; i++) {
            task[i] = new Task("name" + i);
            //10个线程去执行100个任务，肯定有线程执行2个或2个以上的任务
            pool.execute(task[i]);
        }
        //等待任务运行完执行
        pool.shutdown();
        log.info("线程池关闭....");
    }

}
