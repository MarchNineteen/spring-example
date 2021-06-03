package com.wyb.thread.apply.hospital;

import lombok.Data;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author Marcher丶
 * <p>
 * 叫号窗口
 * </p>
 */
@Data
public class Window implements Runnable, WindowCallNum {

    private final NumCenter numCenter;

    /**
     * 窗口名称
     */
    private String windowName;

    /**
     * 窗口所分配到的号子
     */
    private List<Integer> allNumbers;

    /**
     * 叫号
     */
    private List<Integer> callNumbers;

    /**
     * 过号
     */
    private List<Integer> passNums;

    /**
     * 大屏
     */
    private Display display;

    public Window(NumCenter numCenter) {
        this.numCenter = numCenter;
    }

    @Override
    public void callNum() {
        ExecutorService windowService = numCenter.getWindowService();
        windowService.submit(this);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (numCenter) {
                if (numCenter.getNumber() > 0) {
                    Integer number = numCenter.getNumber();
                    System.out.println("请" + number + "号到" + Thread.currentThread().getName() + "抽血");
                    numCenter.setNumber(numCenter.getNumber() - 1);
                    System.out.println("剩余号子" + numCenter.getNumber() + "张");
                    try {
                        Thread.sleep(1000);
                        numCenter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // display.callNumber();
                } else {
                    System.out.println(Thread.currentThread().getName() + "等待有人挂号");
                    numCenter.notify();
                }

            }
        }
    }
}
