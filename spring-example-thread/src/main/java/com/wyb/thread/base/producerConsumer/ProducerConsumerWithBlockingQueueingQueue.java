package com.wyb.thread.base.producerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wangyb
 * 生产者消费者 BlockingQueueingQueue 实现
 * <p>
 * 实现思路：
 * 在缓存区设立缓存队列，生产者线程向队列中添加资源，消费者消费资源
 * </p>
 */
public class ProducerConsumerWithBlockingQueueingQueue {
    public static void main(String[] args) {

        BlockingQueueCommonResource resource = new BlockingQueueCommonResource();
        BlockingQueueProduce p1 = new BlockingQueueProduce(resource);
        BlockingQueueProduce p2 = new BlockingQueueProduce(resource);
        BlockingQueueProduce p3 = new BlockingQueueProduce(resource);

        BlockingQueueConsume c1 = new BlockingQueueConsume(resource);
        BlockingQueueConsume c2 = new BlockingQueueConsume(resource);
        BlockingQueueConsume c3 = new BlockingQueueConsume(resource);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}

//公共类 存放资源
class BlockingQueueCommonResource {

    private BlockingQueue resourceQueue = new LinkedBlockingQueue(10);

    public void add() {
        try {
            resourceQueue.put(1);
            System.out.println("生产者" + Thread.currentThread().getName() + "生产一个资源," + "当前资源池有" + resourceQueue.size() + "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        try {
            resourceQueue.take();
            System.out.println("消费者" + Thread.currentThread().getName() + "消耗一个资源," + "当前资源池有" + resourceQueue.size() + "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 生产者线程
class BlockingQueueProduce extends Thread {
    private BlockingQueueCommonResource resource;

    public BlockingQueueProduce(BlockingQueueCommonResource resource) {
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
class BlockingQueueConsume extends Thread {
    private BlockingQueueCommonResource resource;

    public BlockingQueueConsume(BlockingQueueCommonResource resource) {
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
