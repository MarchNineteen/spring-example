package com.wyb.mybatis.controller;


import com.wyb.mybatis.dao.model.UserDo;
import com.wyb.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date: 2018-01-07 19:57
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<UserDo> list(){
        return userService.listAll(1,10);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam("username")String name,
                               @RequestParam("pwd")String pwd){
        if (name.equals("111111") && pwd.equals("123456")){
            log.info("登录请求");
            return true;
        }
        return false;

    }
}
