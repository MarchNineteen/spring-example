package com.wyb.thread.apply;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Marcher丶
 * 现有的程序代码模拟产生了16个日志对象，并且需要运行16秒才能打印完这些日志，请在程序中增加4个线程去调用parseLog()方法来分头打印这16个日志对象，
 * 程序只需要运行4秒即可打印完这些日志对象。
 */
public class LogParse {

    public static void main(String[] args){
        //定义一个线程共享的队列容器，可以使得数据由队列的一端输入，从另外一端输出
        final BlockingQueue<String> queue=new ArrayBlockingQueue<String>(16);
        for(int i=0;i<4;i++){
            new Thread(new Runnable(){

                @Override
                public void run() {
                    while(true){
                        try {
                            if (queue.isEmpty()) {
                                break;
                            }
                            parseLog(queue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }).start();
        }

        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        /*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
        修改程序代码，开四个线程让这16个对象在4秒钟打完。
        */
        for(int i=0;i<16;i++){  //这行代码不能改动
            final String log = ""+(i+1);//这行代码不能改动
            {
                try {
                    queue.put(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //parseLog方法内部的代码不能改动
    public static void parseLog(String log){
        System.out.println(log+":"+(System.currentTimeMillis()/1000) + "，" + Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

