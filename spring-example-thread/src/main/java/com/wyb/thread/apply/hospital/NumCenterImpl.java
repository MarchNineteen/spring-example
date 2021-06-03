package com.wyb.thread.apply.hospital;

import com.wyb.thread.pool.threadPoolExecutor.config.Config;
import com.wyb.thread.pool.threadPoolExecutor.utils.NamedThreadFactory;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

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

    private ExecutorService registeredService;
    private ExecutorService windowService;

    /**
     * 初始化系统
     */
    public void init(Config config) {
        registeredService = new ThreadPoolExecutor(config.parallelThreads(), config.parallelThreads(), 0, TimeUnit.MILLISECONDS,
                config.queueSize() == 0 ? new SynchronousQueue<>()
                        : (config.queueSize() < 0 ? new LinkedBlockingQueue<>()
                        : new LinkedBlockingQueue<>(config.queueSize())), new NamedThreadFactory("挂号机"));

        windowService = new ThreadPoolExecutor(config.parallelThreads(), config.parallelThreads(), 0, TimeUnit.MILLISECONDS,
                config.queueSize() == 0 ? new SynchronousQueue<>()
                        : (config.queueSize() < 0 ? new LinkedBlockingQueue<>()
                        : new LinkedBlockingQueue<>(config.queueSize())), new NamedThreadFactory("窗口"));

        // 注册2个挂号机
        for (int i = 1; i <= 2; i++) {
            machines.add(new RegisteredMachine(this));
        }
        // 模拟叫号
        for (int i = 1; i <= 2; i++) {
            Window window = new Window(this);
            windows.add(window);
            window.callNum();
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

    @Override
    public RegisteredMachine getRandomMachine() {
        if (CollectionUtils.isEmpty(machines)) {
            throw new RuntimeException("当前系统没有挂号机");
        }
        Random random = new Random();
        return machines.get(random.nextInt(machines.size()));
    }

    @Override
    public Window getRandomWindow() {
        if (CollectionUtils.isEmpty(windows)) {
            throw new RuntimeException("当前系统没有挂号机");
        }
        Random random = new Random();
        return windows.get(random.nextInt(windows.size()));
    }

    @Override
    public ExecutorService getRegisteredService() {
        return registeredService;
    }

    @Override
    public ExecutorService getWindowService() {
        return windowService;
    }
}
