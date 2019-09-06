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
 * @author: Kunzite
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
}
