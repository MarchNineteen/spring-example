package com.wyb.thread.apply.hospital;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author Marcher丶
 * <p>
 * 挂号机
 * </p>
 */
@Slf4j
public class RegisteredMachine implements Callable<Integer> {

    private final NumCenter numCenter;

    public RegisteredMachine(NumCenter numCenter) {
        this.numCenter = numCenter;
        numCenter.addRegisteredMachine(this);
    }

    /**
     * 用户扫描增加号子
     */
    @SneakyThrows
    public Integer addNum() {
        ExecutorService executorService = numCenter.getRegisteredService();
        Future<Integer> future = executorService.submit(this);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer call() {
        synchronized (numCenter) {
            Integer currentNum = numCenter.getNumber();
            // System.out.println("当前" + Thread.currentThread().getName() + ", 添加之前号子数" + numCenter.getNumber() + "张");
            numCenter.setNumber(currentNum + 1);
            System.out.println("当前" + Thread.currentThread().getName() + ", 添加之后号子数" + numCenter.getNumber() + "张");
            return numCenter.getNumber();
        }
    }
}

