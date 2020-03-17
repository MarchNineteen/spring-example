package com.wyb.thread.apply;

/**
 * @author Marcher丶
 * 设计四个线程,其中两个线程每次对变量i加1,另外两个线程每次对i减1.
 */
public class FourThreadAddSub {
    private int i = 0;

    public static void main(String[] args) {
        FourThreadAddSub demo=new FourThreadAddSub();
        AddThread add = demo.new AddThread();
        SubThread sub = demo.new SubThread();
        for(int i=1;i<=2;i++){
            new Thread(add,"线程"+i).start();
            new Thread(sub, "线程" + i).start();
        }

    }
    class AddThread extends Thread{

        @Override
        public void run() {
            for(int i=0;i<10;i++){
                addOne();
            }
        }
     }

    class SubThread extends Thread {
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                subOne();
            }
        }
    }

    public synchronized void addOne() {
        i++;
        System.out.println(Thread.currentThread().getName()+"加一的值为:"+i);
    }
    public synchronized void subOne(){
        i--;
        System.out.println(Thread.currentThread().getName()+"减一的值为:"+i);
    }
}
