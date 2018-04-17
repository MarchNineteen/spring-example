package com.wyb.shiro.controller;

import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/list")
    public List<UserDo> listByPage(){
        return userService.listByPage();
    }
}
