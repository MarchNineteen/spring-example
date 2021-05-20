package com.wyb.thread.apply.hospital;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Marcher丶
 *         <p>
 *         挂号机
 *         </p>
 */
@Slf4j
public class RegisteredMachine implements Runnable {

    private final NumCenter numCenter;

    public RegisteredMachine(NumCenter numCenter) {
        this.numCenter = numCenter;
        numCenter.addRegisteredMachine(this);
    }

    /**
     * 用户扫描增加号子
     */
    public void addNum() {
        synchronized (numCenter) {
            // System.out.println("当前" + Thread.currentThread().getName() + ", 添加之前号子数" + numCenter.getNumber() + "张");
            numCenter.setNumber(numCenter.getNumber() + 1);
            System.out.println("当前" + Thread.currentThread().getName() + ", 添加之后号子数" + numCenter.getNumber() + "张");
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void run() {
        while (true) {
            addNum();
        }
    }
}
