package com.wyb.aop.controller;


import com.wyb.aop.annotation.Log;
import com.wyb.aop.dao.model.UserDo;
import com.wyb.aop.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @Log(value = "add")
    @Log(value = "update")
    @GetMapping("/list")
    public List<UserDo> list(){
        System.out.println("controller start");
        return userService.listAll(1,10);
    }
}
