package com.wyb.cache.controller;

import com.wyb.cache.dao.model.UserDo;
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
 * @author: Kunzite
 * @version: 2018-02-01 15:57
 */
@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    UserService userService;

//    public CacheService cache = CacheFactory.getCache("REDIS");

//    @Resource
//    CacheService cache;

    @GetMapping("/add")
    public UserDo addById(){
        UserDo userDo = userService.getById("1");
        CacheService cache = SpringContextHolder.getBean("redisServiceImpl");
        cache.putCache(String.valueOf(userDo.getId()),userDo);
        return userDo;
    }

    @GetMapping("/get")
    public UserDo getById(){
        CacheService cache = SpringContextHolder.getBean("redisServiceImpl");
        return (UserDo) cache.getCache("1");
    }
}
