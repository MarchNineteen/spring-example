package com.wyb.thread.apply.hospital;

import java.util.List;

/**
 * @author Marcher丶
 *
 *         <p>
 *         大屏叫号
 *         </p>
 */
public class CallNumDisplay implements Display {

    /**
     * 叫号窗口
     */
    private List<Window> windows;

    // 叫号超时时间
    public long overTime;


    @Override
    public void addWindow(Window window) {
        windows.add(window);
    }

    @Override
    public void callNumber() {

    }
}
