package com.wyb.cache.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyb.cache.constant.CacheType;
import com.wyb.cache.dao.model.UserDo;
import com.wyb.cache.factory.CacheFactory;
import com.wyb.cache.service.CacheService;
import com.wyb.cache.service.UserService;

import lombok.extern.slf4j.Slf4j;

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

    @Resource
    private UserService userService;

    @Resource
    private CacheFactory cacheFactory;

    @GetMapping("/add")
    public UserDo addById() {
        UserDo userDo = userService.getById(1);
        CacheService cache = cacheFactory.getCache(CacheType.REDIS);
        cache.putCache(String.valueOf(userDo.getId()), userDo);
        userDo = (UserDo) cache.getCache(userDo.getId().toString());
        return userDo;
    }

    @GetMapping("/get")
    public UserDo getById() {
        CacheService cache = cacheFactory.getCache(CacheType.REDIS);
        return (UserDo) cache.getCache("1");
    }

    @GetMapping("/lock")
    public String lock() {
        CacheService cache = cacheFactory.getCache(CacheType.REDIS);
        try {
            boolean t = cache.tryLock("LipapayOrderQueryScheduled.checkProcessOrderStatus", 2000, 2000);
            String s = Thread.currentThread().getName() + "=====================";
            if (!t) {
                return "服务繁忙，请退出重试";
            }
            Integer stock;
            // 拿到分布式锁 去取库存
            UserDo userDo = userService.getById(1);
            if ((stock = userDo.getAge()) <= 0) {
                System.out.println("下单失败，已经没有库存了");
                return "下单失败，已经没有库存了";
            }
            stock--;
            userService.updateAgeById(stock, userDo.getId());
            System.out.println(s + "下单成功，当前剩余产品数：" + stock);
            return "下单成功，当前剩余产品数：" + stock;
        }
        catch (Exception e) {
            log.error("[LipapayOrderQueryScheduled][checkProcessOrderStatus] process error, e {}", e);
        }
        finally {
            cache.unlock("LipapayOrderQueryScheduled.checkProcessOrderStatus");
        }
        return "服务繁忙，请退出重试";
    }
}
