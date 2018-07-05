package com.wyb.shiro.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.result.web.WebResult;
import com.wyb.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/list")
    public String listByPage(
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
            Map<String, Object> map) {
        PageInfo<UserDo> page = userService.listByPage(pageNum, pageSize);
        map.put("page", page);
        return "admin/user/userList";
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public WebResult addUser(@RequestParam(value = "username", required = true) String userName,
                             @RequestParam(value = "password", required = true) String password
    ) {
        log.info("添加账号：{},密码：{}", userName, password);
        int i = userService.addUser(userName, password);
        if (i > 0) {
            return WebResult.success();
        }
        return null;
    }
}
