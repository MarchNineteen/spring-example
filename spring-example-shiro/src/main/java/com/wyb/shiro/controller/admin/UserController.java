package com.wyb.shiro.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.result.web.WebResult;
import com.wyb.shiro.service.UserService;
import com.wyb.shiro.utils.PageUtil;
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
        String pageHTML = PageUtil.getPageContent("/admin/sys/role/list_${pageCurrent}_${pageSize}_${pageCount}", page.getPageNum(), page.getPageSize(), page.getPages());
        map.put("pageHTML", pageHTML);
        map.put("list", page.getList());
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
