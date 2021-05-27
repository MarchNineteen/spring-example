package com.wyb.cache.controller;

import com.wyb.cache.constant.CacheType;
import com.wyb.cache.dao.model.UserDo;
import com.wyb.cache.factory.CacheFactory;
import com.wyb.cache.service.CacheService;
import com.wyb.cache.service.UserService;
import com.wyb.cache.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author: Marcher丶
 * @version: 2018-02-01 15:57
 */
@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {

    private int num = 20;

    @Resource
    private UserService userService;

    @Resource
    private CacheFactory cacheFactory;

    @GetMapping("/add")
    public UserDo addById(){
        UserDo userDo = userService.getById("1");
        CacheService cache = cacheFactory.getCache(CacheType.REDIS);
        cache.putCache(String.valueOf(userDo.getId()),userDo);
        userDo = (UserDo) cache.getCache(userDo.getId().toString());
        return userDo;
    }

    @GetMapping("/get")
    public UserDo getById(){
        CacheService cache = cacheFactory.getCache(CacheType.REDIS);
        return (UserDo) cache.getCache("1");
    }

    @GetMapping("/lock")
    public void checkProcessOrderStatus() {
        CacheService cache = cacheFactory.getCache(CacheType.REDIS);

        try {
            boolean t = cache.tryLock("LipapayOrderQueryScheduled.checkProcessOrderStatus",
                    2000, 2000);
            String s = Thread.currentThread().getName() + "=====================";
            if (num > 0) {
                System.out.println(s + "排号成功，号码是：" + num);
                num--;
            } else {
                System.out.println(s + "排号失败,号码已经被抢光");
            }


//            System.out.println("线程：" + Thread.currentThread().getName() + "获取锁" + (t ? "成功" : "失败"));

            cache.unlock( "LipapayOrderQueryScheduled.checkProcessOrderStatus");
        } catch (Exception e) {
            log.error("[LipapayOrderQueryScheduled][checkProcessOrderStatus] process error, e {}", e);
        }
    }
}
