package com.wyb.cache;

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
public class ZsetTests {

    public static final String DELAY_QUEUE = "redis.zset.delay.queen";
    public static final long DELAY_TIME = 1;
    public static final String CHANNEL = "zet.delay.channel";

    @Resource
    private CacheService redisService;

    /**
     *
     */
    @Test
    public void DelayQueen() {
        redisService.zput(DELAY_QUEUE, "value", System.currentTimeMillis() + DELAY_TIME * 1000);
        redisService.publish(CHANNEL, "value");
        System.out.println("发送消息 over");
    }
}
