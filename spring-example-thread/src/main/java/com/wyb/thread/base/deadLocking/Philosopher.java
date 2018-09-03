package com.wyb.thread.base.deadLocking;

import java.util.Random;

/**
 * @author Kunzite
 * <p>
 *  哲学家类 每个线程就相当于一个哲学家
 * </p>
 */
public class Philosopher implements Runnable{

    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int ident, int ponder){
        this.left = left;
        this.right = right;
        this.id = ident;
        this.ponderFactor = ponder;
    }


    @Override
    public void run(){
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + "" + "thinking");
                pause();

                System.out.println(this+" start to eat and take right stick");
                //该哲学家首先拿起右边的筷子
                right.take();

                System.out.println(this+" take left stick");
                left.take(); // 该哲学家然后拿起左边的筷子，此处可能发生的情况：1、成功拿起；2、等待左边的哲学家drop掉右手的筷子，

                //若每一个哲学家都拿着右手的等着左手的筷子，并且最后一个哲学家左手等待第一个哲学家右手的筷子，就会循坏等待发生死锁。
                System.out.println(this + " " + "is eating");//若该哲学家成功拿起左右手筷子，则吃饭，下一步并释放手中筷子

                pause();
                right.drop();
                left.drop();
            }
        }catch (Exception e) {
            System.out.println(this + " " + "exiting via interruption");
        }

    }


    // 暂停一段时间
    private void pause() throws InterruptedException {
//        if(ponderFactor == 0) {
//            return;
//        }
//        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
        Thread.sleep(ponderFactor*100);
    }

    @Override
    public String toString(){
        return "Philosopher" + id;
    }

}
