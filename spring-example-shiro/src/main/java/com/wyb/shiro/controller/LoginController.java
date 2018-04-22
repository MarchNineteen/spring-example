package com.wyb.shiro.controller;

import com.wyb.shiro.realm.UserToken;
import com.wyb.shiro.result.WebResult;
import com.wyb.shiro.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kunzite
 */
@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResult login(
            @RequestParam(value = "username", required = true) String userName,
            @RequestParam(value = "password", required = true) String password
    ) {
        log.info("==========" + userName + password);
        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//        token.setRememberMe(rememberMe);
        String token = JWTUtil.sign(userName,password);
        try {
            subject.login(new UserToken(token));
        } catch (AuthenticationException e) {
            e.printStackTrace();
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
