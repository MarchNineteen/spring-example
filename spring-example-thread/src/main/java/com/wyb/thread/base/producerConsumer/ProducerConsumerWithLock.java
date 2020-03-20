package com.wyb.thread.base.producerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kunzite
 * 生产者消费者 lock 实现
 */
public class ProducerConsumerWithLock {

    public static void main(String[] args) {
        LockCommonResource resource = new LockCommonResource();
        LockProduce p1 = new LockProduce(resource);
        LockProduce p2 = new LockProduce(resource);
        LockProduce p3 = new LockProduce(resource);

        LockConsume c1 = new LockConsume(resource);
        LockConsume c2 = new LockConsume(resource);
        LockConsume c3 = new LockConsume(resource);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
//        c2.start();
//        c3.start();
    }
}

//公共类 存放资源
class LockCommonResource {

    //对象锁
    private Lock lock = new ReentrantLock();
    //生产者
    private Condition produceCondition = lock.newCondition();
    //消费者
    private Condition consumeCondition = lock.newCondition();

    // 当前资源数
    private int num;
    //最大资源数
    private int maxNum = 10;

    public void add() {
        lock.lock();
        try {
            //资源溢出
            if (num < maxNum) {
                num++;
                System.out.println(Thread.currentThread().getName() + "生产一个资源，当前资源池有" + num + "个");
                consumeCondition.signalAll();//唤醒消费者等待线程
            } else {
                try {
                    System.out.println("生产者" + Thread.currentThread().getName() + "线程进入等待状态");
                    produceCondition.await();//生产者线程进入等待池
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void remove() {
        lock.lock();
        try {
            if (num > 0) {
                num--;
                System.out.println("消费者" + Thread.currentThread().getName() + "消化了一个资源,当前资源池还有" + num + "个资源");
                produceCondition.signalAll();//唤醒等待的生产者
            } else {
                //不存在资源时 消费者进入等待池
                try {
                    System.out.println("消费者" + Thread.currentThread().getName() + "线程进入等待状态");
                    consumeCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

    }
}

// 生产者线程
class LockProduce extends Thread {
    private LockCommonResource resource;

    public LockProduce(LockCommonResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        // 使用while true 进入无限循环
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}

// 消费者线程
class LockConsume extends Thread {
    private LockCommonResource resource;

    public LockConsume(LockCommonResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        // 使用while true 进入无限循环
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}
