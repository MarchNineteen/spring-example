package com.wyb.cache.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyb.cache.dao.model.UserDo;
import com.wyb.cache.service.UserService;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date: 2018-01-07 19:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    @Cacheable(value = "user")
    public List<UserDo> list() {
        return userService.listAll(1, 10);
    }

    @GetMapping("/getById")
    @Cacheable(value = "user", key = "#id")
    public UserDo getById(String id) {
        return userService.getById(id);
    }

    @RequestMapping("/updateUser")
    @CachePut(value = "user", key = "#userDo.id")
    public UserDo updateUser(UserDo userDo) {
        return userService.updateUserNameById(userDo);
    }

    @RequestMapping("/removeUser")
    @CacheEvict(value = "user", key = "#userDo.id", allEntries = true)
    public String removeUser(UserDo userDo) {
        return userService.removeUserById(userDo) ? "删除成功" : "删除失败";
    }

}
