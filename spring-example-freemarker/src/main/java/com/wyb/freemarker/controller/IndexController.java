package com.wyb.freemarker.controller;

import com.wyb.freemarker.dao.model.UserDo;
import com.wyb.freemarker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-06 16:32
 */
@Controller
public class IndexController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String freemarker(UserDo userDo,HttpServletRequest request,HashMap<String,Object> map){
        String username = userDo.getUsername();
        String pwd = request.getParameter("password");
        UserDo userDo1 = userService.getByName(username);
        if (pwd.equals(userDo1.getPassword())){
            map.put("name", "Kunzite");
            map.put("sex", 1);    //sex:性别，1：男；0：女；
            // 模拟数据
            List<UserDo> userDoList = userService.listAll(1,10);
            map.put("users", userDoList);
            return "index";
        }
        return "error";

    }
}
