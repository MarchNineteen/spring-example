package com.wyb.thread.pool.threadPoolExecutor;

import com.wyb.thread.pool.threadPoolExecutor.config.Config;
import com.wyb.thread.pool.threadPoolExecutor.utils.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * JDK提供的线程池API ThreadPoolExecutor
 *
 * @author Kunzite
 */
@Slf4j
public class ThreadPoolExecutorDemo {

    private Config config;
    // 线程池生命周期
    private ExecutorService executorService;

    public ThreadPoolExecutorDemo(Config config) {
        this.config = config;
        //构造线程池
        this.executorService = new ThreadPoolExecutor(config.parallelThreads(), config.parallelThreads(), 0, TimeUnit.MILLISECONDS,
                config.queueSize() == 0 ? new SynchronousQueue<>()
                        : (config.queueSize() < 0 ? new LinkedBlockingQueue<>()
                        : new LinkedBlockingQueue<>(config.queueSize())), new NamedThreadFactory("task"));
    }

    public void run(String url) {
        for (int num = 0; num < 300; num += 30) {
            String newUrl = url + num;
            log.info("{},请求的路径为：{}", Thread.currentThread(), newUrl);
            executorService.submit(new DownloadThread(new Request(newUrl, config)));
        }
        executorService.shutdown();
        log.info("线程池关闭");
    }

    public static void main(String[] args) {
        ThreadPoolExecutorDemo demo = new ThreadPoolExecutorDemo(new Config());
        demo.run("http://maoyan.com/films?showType=1&offset=");
    }


}
