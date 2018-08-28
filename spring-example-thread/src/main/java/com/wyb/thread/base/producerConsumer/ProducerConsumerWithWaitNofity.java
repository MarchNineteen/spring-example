package com.wyb.thread.base.producerConsumer;

/**
 * @author Kunzite
 * 生产者消费者 synchronized、wait和notify 实现
 */
public class ProducerConsumerWithWaitNofity  {
    public static void main(String[] args) {
        CommonResource resource = new CommonResource();
        Produce p1 = new Produce(resource);
        Produce p2 = new Produce(resource);
        Produce p3 = new Produce(resource);

        //消费者线程
        Consume c1 = new Consume(resource);
        Consume c2 = new Consume(resource);
        Consume c3 = new Consume(resource);

        p1.start();
//        p2.start();
//        p3.start();
        c1.start();
//        c2.start();
//        c3.start();
    }

}

// 生产者线程
class Produce extends Thread {
    private CommonResource resource;

    public Produce(CommonResource resource) {
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
class Consume extends Thread {
    private CommonResource resource;

    public Consume(CommonResource resource) {
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

//公共类 存放资源
class CommonResource {

    // 当前资源数
    private int num;
    //最大资源数
    private int maxNum = 10;

    public synchronized void remove() {
        if (num > 0) {
            num--;
            System.out.println("消费者" + Thread.currentThread().getName() + "消化了一个资源,当前资源池还有" + num + "个资源");
            notifyAll();//通知生产者生产资源
        } else {
            //不存在资源时 消费者进入等待池
            try {
                System.out.println("消费者" + Thread.currentThread().getName() + "线程进入等待状态");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void add() {
        //资源溢出
        if (num == maxNum) {
            try {
                System.out.println("生产者" + Thread.currentThread().getName() + "线程进入等待状态");
                wait();//生产者线程进入等待池
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num++;
            System.out.println(Thread.currentThread().getName() + "生产一个资源，当前资源池有"  + num + "个");
            notifyAll();//通知消费者消费资源
        }
    }
}