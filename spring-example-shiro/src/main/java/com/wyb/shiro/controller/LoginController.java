package com.wyb.shiro.controller;

import com.wyb.shiro.config.JWTConfig;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.realm.UserToken;
import com.wyb.shiro.result.WebResult;
import com.wyb.shiro.service.UserService;
import com.wyb.shiro.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Kunzite
 */
@Slf4j
@Controller
public class LoginController {

    @Resource
    private JWTConfig jwtConfig;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResult login(
            @RequestParam(value = "username", required = true) String userName,
            @RequestParam(value = "password", required = true) String password
    ) {
        log.info("登录账号：{},密码：{}" ,userName , password);
        Subject subject = SecurityUtils.getSubject();
        UserDo user = userService.getByUserName(userName);
        if (null == user) {
            return WebResult.success("用户不存在");
        }
//        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//        token.setRememberMe(rememberMe);
        String token = JWTUtil.sign(user.getId(), password, jwtConfig.getIssure());
        try {
            subject.login(new UserToken(token));
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            WebResult webResult = WebResult.illegalArgument("username",e.getMessage());
            return webResult;
//            rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
//            return "{\"Msg\":\"您的账号或密码输入错误\",\"state\":\"failed\"}";
        }
        return WebResult.success(token);
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}
