package com.wyb.thread.base.synchronize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 验证 wait()  notify() 对锁的释放
 * 因为线程A先获得了cpu时间片，虽然线程B已经start 但是只是进入就绪态，还未真正运行
 */
public class ThreadA {

    private final static Logger logger = LoggerFactory.getLogger(ThreadA.class);

    public static void main(String[] args) throws InterruptedException{
        ThreadB b = new ThreadB();
        //启动计算线程
        b.start();
        //线程A拥有b对象上的锁。线程为了调用wait()或notify()方法，该线程必须是那个对象锁的拥有者
        synchronized (b) {
            System.out.println("执行A的锁");
            System.out.println("等待对象b完成计算。。。");
            //当前线程A等待
            b.wait();
            System.out.println("b对象计算的总和是：" + b.total);
        }
    }
}



/**
 * 计算1+2+3 ... +100的和
 *
 */
class ThreadB extends Thread {
    int total;

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 101; i++) {
                total += i;
            }
            System.out.println("执行B的锁");
            //（完成计算了）唤醒在此对象监视器上等待的单个线程，在本例中线程A被唤醒
            notify();
            System.out.println("计算完成");
        }
    }
}