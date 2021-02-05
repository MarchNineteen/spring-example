package com.wyb.aop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyb.aop.annotation.Log;
import com.wyb.aop.annotation.Logs;
import com.wyb.aop.annotation.TimeLog;
import com.wyb.aop.dao.model.UserDo;
import com.wyb.aop.service.UserService;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date: 2018-01-07 19:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Logs({ @Log("add"), @Log("update") })
    @TimeLog
    @GetMapping("/list")
    public List<UserDo> list(@RequestParam(required = false) Integer pageNum) {
        System.out.println("controller start");
        return userService.listAll(1, 10);
    }
}
