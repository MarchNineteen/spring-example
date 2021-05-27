package com.wyb.cache.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import com.wyb.cache.service.CacheService;

/**
 * redis 订阅
 *
 * @author Marcher丶
 */
@Component("zsetDelayQueenSubscriber")
public class ZsetDelayQueenSubscriber extends MessageListenerAdapter {
    @Autowired
    private CacheService redisService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String msg = redisTemplate.getStringSerializer().deserialize(body);
        String topic = redisTemplate.getStringSerializer().deserialize(channel);
        System.out.println("监听到topic为" + topic + "的消息：" + msg);

        Set<String> set = (Set<String>) redisService.zsget("redis.zset.delay.queen", 0d,
                (double) System.currentTimeMillis());
        set.forEach(s -> {
            System.out.println(s);
        });
        System.out.println("消费消息 over");
    }

    // public void messageHandler(long messageId) {
    // System.out.println("===");
    // new Thread(() -> { // 放到线程池处理
    //// String message = redisService.hget(DELAY_MESSAGE, String.format(MEMBER_PREFIX, messageId));
    // System.out.println("处理消息体" + message);
    // System.out.println("处理消息体成功");
    // }).start();
    // }

}
