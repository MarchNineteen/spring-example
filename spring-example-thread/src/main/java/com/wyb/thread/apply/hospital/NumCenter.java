package com.wyb.thread.apply.hospital;

import java.util.concurrent.ExecutorService;

/**
 * @author Marcher丶
 *
 * <p>
 * 号码中心
 * </p>
 */
public interface NumCenter {

    /**
     * 注册挂号机
     *
     * @param machine
     */
    public void addRegisteredMachine(RegisteredMachine machine);

    /**
     * 移除挂号机
     *
     * @param machine
     */
    public void removeRegisteredMachine(RegisteredMachine machine);

    public Integer getNumber();

    public void setNumber(Integer number);

    RegisteredMachine getRandomMachine();

    Window getRandomWindow();

    ExecutorService getRegisteredService();

    ExecutorService getWindowService();
}
