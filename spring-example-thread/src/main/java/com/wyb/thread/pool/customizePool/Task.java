package com.wyb.thread.pool.customizePool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 模拟任务
 *
 * @author Kunzite
 */
@Slf4j
public class Task {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task[name=" + name + "]";
    }

    public void doSomething() {
        long sleepTime = new Double(Math.random() * 10).longValue();
        try {
            //模拟任务的执行时间
            log.info("{} doing my task|{}, I need execute {} s", new Object[]{Thread.currentThread().getName(), this, sleepTime});
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error("{} execute task|{} error", new Object[]{Thread.currentThread().getName(), this, e});
        }
        log.info("{} done my task|{}", Thread.currentThread().getName(), this);
    }
}
