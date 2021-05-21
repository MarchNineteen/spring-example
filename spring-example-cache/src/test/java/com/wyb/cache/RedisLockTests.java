package com.wyb.cache;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyb.cache.service.CacheService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Marcher丶
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisLockTests {

    @Resource
    private CacheService redisService;

    /**
     * 测试redis分布式缓存
     *
     * @throws InterruptedException
     */
    @Test
    public void checkProcessOrderStatus() throws InterruptedException {
        int requestNum = 20;
        AtomicInteger num = new AtomicInteger(5);
        final CountDownLatch countDownLatch = new CountDownLatch(requestNum);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < requestNum; i++) {
            threadPool.execute(() -> {
                try {
                    boolean t = redisService.tryLock("LipapayOrderQueryScheduled.checkProcessOrderStatus", 2000, 2000);
                    System.out.println("线程：" + Thread.currentThread().getName() + "获取锁" + (t ? "成功" : "失败"));
                    if (!t) {
                        System.out.println("加锁失败");
                        return;
                    }
                    if (0 == num.get()) {
                        System.out.println("无库存");
                        return;
                    }
                    num.set(num.get() - 1);
                    System.out.println(num.get());
                }
                catch (Exception e) {
                    log.error("[LipapayOrderQueryScheduled][checkProcessOrderStatus] process error, e {}", e);
                }
                finally {
                    countDownLatch.countDown();
                    redisService.unlock("LipapayOrderQueryScheduled.checkProcessOrderStatus");
                }
            });
        }
        countDownLatch.await();
        System.out.println("finish");
    }

}
