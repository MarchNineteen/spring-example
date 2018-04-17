package com.wyb.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/list")
    public PageInfo<UserDo> listByPage(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum, @RequestParam(value = "pageSize",defaultValue = "20") int pageSize) {
        return userService.listByPage(pageNum, pageSize);
    }
}
