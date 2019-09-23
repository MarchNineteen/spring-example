package com.wyb.mybatis.controller;

import com.wyb.mybatis.dao.model.UserDo;
import com.wyb.mybatis.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Marcher丶
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
    public List<UserDo> list() {
        return userService.selectAll();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/getById")
    public UserDo getById(@RequestParam Long id) {
        return userService.selectByKey(id);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public int add(@RequestBody UserDo userDo) {
        return userService.save(userDo);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam("username") String name,
                         @RequestParam("pwd") String pwd) {
        if (name.equals("111111") && pwd.equals("123456")) {
            log.info("登录请求");
            return true;
        }
        return false;

    }
}
