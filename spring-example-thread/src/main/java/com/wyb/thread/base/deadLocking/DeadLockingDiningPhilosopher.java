package com.wyb.thread.base.deadLocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kunzite
 * <p>
 * 问题描述：一圆桌前坐着5位哲学家，两个人中间有一只筷子，桌子中央有面条。
 * 哲学家思考问题，当饿了的时候拿起左右两只筷子吃饭，必须拿到两只筷子才能吃饭。
 * 上述问题会产生死锁的情况，当5个哲学家都拿起自己右手边的筷子，准备拿左手边的筷子时产生死锁现象。
 * <p>
 * 解决办法：
 * 　1、添加一个服务生，只有当经过服务生同意之后才能拿筷子，服务生负责避免死锁发生。
 * 2、每个哲学家必须确定自己左右手的筷子都可用的时候，才能同时拿起两只筷子进餐，吃完之后同时放下两只筷子。
 * 3、规定每个哲学家拿筷子时必须拿序号小的那只，这样最后一位未拿到筷子的哲学家只剩下序号大的那只筷子，不能拿起，剩下的这只筷子就可以被其他哲学家使用，避免了死锁。这种情况不能很好的利用资源。
 * 实现第2种方案
 * https://blog.csdn.net/qq1004642027/article/details/50055917
 * </p>
 */
public class DeadLockingDiningPhilosopher {

    public static void main(String[] args) throws Exception {

//        ExecutorService executor = Executors.newCachedThreadPool();
//        int size=5;
//        int thinkingTime=0;
//        Chopstick[] chopstick = new Chopstick[size];
//        for(int i=0;i<size;i++)
//            chopstick[i] = new Chopstick();
//        for(int i=0;i<size;i++)
//            executor.execute(new Philosopher(chopstick[i], chopstick[(i+1)%size], i, thinkingTime));
//        Thread.sleep(4*1000);
//        executor.shutdownNow();

        int size=5;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        //产生死锁
//        for (int i = 0; i < size; i++) {
////            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, 0)); //n个哲学家n支筷子循坏使用
//            exec.execute(new Philosopher(sticks[i], sticks[(i+1)%size], i, 0));
//        }
        //不会死锁
        for(int i=0;i<size-1;i++)
            exec.execute(new Philosopher(sticks[i], sticks[i+1], i, 0));
        exec.execute(new Philosopher(sticks[0], sticks[size-1], size, 0));//更改第五个哲学家获得筷子的顺序
        Thread.sleep(4*1000);
        exec.shutdownNow();
    }
    /**
     * 注：在这里，需要输入命令行参数，
     * 一共能三个参数分别是：arg[0]：哲学家思考、吃饭、放下筷子的间隔时间，这里设置为0可以让死锁发生的机会变大；args[1]:哲学家数量；args[2]:timeout暂时没想出用处 .
     */

}
