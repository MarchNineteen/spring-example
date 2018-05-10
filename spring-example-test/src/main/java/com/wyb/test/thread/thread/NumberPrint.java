package com.wyb.test.thread.thread;


class NumberPrint implements Runnable {
    private int number;
    public byte res[];
    public static int count = 5;

    public NumberPrint(int number, byte a[]) {
        this.number = number;
        res = a;
    }

    public void run() {
        synchronized (res) {
            while (count-- > 0) {
                try {
                    res.notify();//唤醒等待res资源的线程，把锁交给线程（该同步锁执行完毕自动释放锁）
                    System.out.println(" " + number);
                    res.wait();//释放CPU控制权，释放res的锁，本线程阻塞，等待被唤醒。
                    System.out.println("------线程" + Thread.currentThread().getName() + "获得锁，wait()后的代码继续运行：" + number);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }//end of while
            return;
        }//synchronized

    }

    public static void main(String args[]) {
        final byte a[] = {0};//以该对象为共享资源
        new Thread(new NumberPrint((1), a), "1").start();
        new Thread(new NumberPrint((2), a), "2").start();
    }
}
/**
 *
 * 下面解释为什么会出现这样的结果：
 首先1、2号线程启动，这里假设1号线程先运行run方法获得资源（实际上是不确定的），获得对象a的锁，进入while循环（用于控制输出几轮）：

 1、此时对象调用它的唤醒方法notify()，意思是这个同步块执行完后它要释放锁，把锁交给等待a资源的线程；
 2、输出1；
 3、该对象执行等待方法，意思是此时此刻起拥有这个对象锁的线程（也就是这里的1号线程）释放CPU控制权，释放锁，并且线程进入阻塞状态，后面的代码暂时不执行，因未执行完同步块，所以1也没起作用；
 4、在这之前的某时刻线程2运行run方法，但苦于没有获得a对象的锁，所以无法继续运行，但3步骤之后，它获得了a的锁，此时执行a的唤醒方法notify(),同理，意思是这个同步块执行完后它要释放锁，把锁交给等待a资源的线程；
 5、输出2；
 6、执行a的等待方法，意思是此时此刻起拥有这个对象锁的线程（也就是这里的2号线程）释放CPU控制权，释放锁，并且线程进入阻塞状态，后面的代码暂时不执行，因未执行完同步块，所以2号线程的4步骤的唤醒方法也没起作用；
 7、此时1号线程执行到3步骤，发现对象锁没有被使用，所以继续执行3步骤中wait方法后面的代码，于是输出：------线程1获得锁，wait()后的代码继续运行：1；
 8、此时while循环满足条件，继续执行，所以，再执行1号线程的唤醒方法，意思是这个同步块执行完后它要释放锁；
 9、输出1；
 10、执行等待方法，线程1阻塞，释放资源锁；
 11、此时线程2又获得了锁，执行到步骤6，继续执行wait方法后面的代码，所以输出：------线程2获得锁，wait()后的代码继续运行：2；
 12、继续执行while循环，输出2；
 ··· ···
 通过上述步骤，相信大家已经明白这两个方法的使用了，但该程序还存在一个问题，当while循环不满足条件时，肯定会有线程还在等待资源，所以主线程一直不会终止。当然这个程序的目的仅仅为了给大家演示这两个方法怎么用。
 */
