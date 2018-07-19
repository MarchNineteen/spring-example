package com.wyb.thread.pool.customizePool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 线程池实现类
 *
 * @author Kunzite
 */
@Slf4j
public class ThreadPool implements Pool<PoolThread> {

    //默认线程池的线程数
    private static final int DEFAULT_SIZE = 5;
    //线程池中的空闲线程，用队列表示
    private BlockingQueue<PoolThread> idleThreads;
    //设置线程池是否关闭的标志
    private volatile boolean isShutdown = false;
    //存放所有线程池的所有线程，不管是否在执行任务
    private static Set<Thread> threadSet = new HashSet<>();

    public ThreadPool(){
        this(DEFAULT_SIZE);
    }

    public ThreadPool(int threadSize){
        this.idleThreads = new LinkedBlockingQueue<>(threadSize);
        for(int i=0; i<threadSize; i++){
            PoolThread thread = new PoolThread(this);
            thread.setName("ThreadPool-"+i);
            thread.start();
            idleThreads.add(thread);
            threadSet.add(thread);
        }
    }


    @Override
    public void shutdown() {
        this.setShutdown(true);
        int threadSize = idleThreads.size();
        log.debug("threadPool all has {} thread.", threadSize);
        log.debug("closing pool........");
        for(int i=0; i<threadSize; i++){
            PoolThread thread = borrowFromPool();
            thread.setTask(null);
            thread.setIdleLocal(null);
            thread.close();
        }
        idleThreads.clear();
        threadSet.clear();
    }

    @Override
    public void shutdownnow() {
        log.info("pool is shutdown now!!!");
        setShutdown(true);
        for(Thread thread : threadSet){
            if(!thread.isInterrupted()){
                log.info("{} will be interrupt.", thread.getName());
                thread.interrupt();
            }
        }
    }

    @Override
    public void execute(Task task) {
        PoolThread thread = borrowFromPool();
        log.debug("I will set the task|{} soon...", task);
        thread.setTask(task);
    }

    @Override
    public PoolThread borrowFromPool() {
        PoolThread thread = null;
        try {
            log.debug("borrow thread from pool, pool all has {} threads .", idleThreads.size());
            thread = idleThreads.take();
            log.debug("thread {} borrow from pool, pool all has {} threads", thread.getName(), idleThreads.size());
        } catch (InterruptedException e) {
            log.error("borrow from pool error",e);
        }
        return thread;
    }

    @Override
    public void returnToPool(PoolThread t) {
        try {
            log.debug("thread {} return to pool", t.getName());
            idleThreads.offer(t, 1L, TimeUnit.SECONDS);
            log.debug("thread {} return to pool, pool all has {} threads", t.getName(), idleThreads.size());
        } catch (InterruptedException e) {
            log.error("thread {} return to pool error ", t.getName(), e);
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    @Override
    public void setShutdown(boolean isShutdown) {
        this.isShutdown = isShutdown;
    }
}
