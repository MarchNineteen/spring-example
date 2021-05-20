package com.wyb.thread.apply.hospital;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Marcher丶
 */
@Data
public class NumCenterImpl implements NumCenter {

    // 挂号机
    private List<RegisteredMachine> machines = new ArrayList<>();

    // 叫号窗口
    private List<Window> windows = new ArrayList<>();

    // 叫号显示大屏
    private CallNumDisplay callNumDisplay;

    /**
     * 号子总数
     */
    private Integer number = 0;

    /**
     * 初始化系统
     */
    public void init() {
        // 模拟挂号
        for (int i = 1; i <= 2; i++) {
            Thread thread = new Thread(new RegisteredMachine(this));
            thread.setName("挂号机" + i);
            thread.start();
        }
        // // 模拟叫号
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new Window(this));
            thread.setName("窗口" + i);
            thread.start();
        }
    }

    @Override
    public void addRegisteredMachine(RegisteredMachine machine) {
        machines.add(machine);
    }

    @Override
    public void removeRegisteredMachine(RegisteredMachine machine) {
        machines.remove(machine);
    }

}
