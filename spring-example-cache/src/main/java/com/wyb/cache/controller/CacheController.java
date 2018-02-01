package com.wyb.cache.controller;

import com.wyb.cache.dao.model.UserDo;
import com.wyb.cache.factory.Cache;
import com.wyb.cache.factory.CacheFactory;
import com.wyb.cache.service.UserService;
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

    public static Cache cache = CacheFactory.getCache("REDIS");

    @GetMapping("/byId")
    public UserDo cacheById(){
        UserDo userDo = userService.getById("1");
        String s = cache.setValue("name",userDo.getUsername());
        return userDo;
    }
}
