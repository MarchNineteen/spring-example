package com.wyb.timer.controller;


import com.wyb.timer.dao.model.UserDo;
import com.wyb.timer.service.UserService;
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

    @GetMapping("/list")
    public List<UserDo> list(){
        return userService.listAll(1,10);
    }
}
