package com.wyb.cache;

import com.wyb.cache.dao.model.UserDo;
import com.wyb.cache.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Marcher丶
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisMqTests {

    @Resource
    private CacheService redisService;

    @Test
    public void testMq() {
        String listKey = "redis.mq";
        for (int i = 0; i < 10; i++) {
            UserDo userDo = new UserDo();
            userDo.setUsername("这是第" + i + "个用户");
            redisService.qpush(listKey, userDo);
        }
        for (int i = 0; i < 10; i++) {
            UserDo userDo = (UserDo) redisService.qpop(listKey);
            System.out.println("获取第" + i + "个用户，名字是" + userDo.getUsername());
        }
    }

    @Test
    public void testMqPublishAndSubscribe() {
        redisService.publish("channel 1" , "msg 1");
    }

}
