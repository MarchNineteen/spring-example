package com.wyb.shiro.controller.admin;

import com.wyb.shiro.config.JWTConfig;
import com.wyb.shiro.dao.dto.UserDto;
import com.wyb.shiro.help.UserPasswordHelper;
import com.wyb.shiro.realm.UserToken;
import com.wyb.shiro.result.web.WebResult;
import com.wyb.shiro.service.UserService;
import com.wyb.shiro.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Kunzite
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private JWTConfig jwtConfig;
    @Resource
    private UserService userService;
    @Resource
    private UserPasswordHelper passwordHelper;

    /**
     * 跳转登录
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 登录成功跳转
     *
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "admin/index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResult login(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        log.info("登录账号：{},密码：{}", username, password);
//        UserDto userDto = userService.getByUserName(username);
//        if (null == userDto) {
//            return WebResult.illegalArgument("username", "用户不存在");
//        }
//        token.setRememberMe(rememberMe);
        UserToken userToken = new UserToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        String message = "";
        try {
            currentUser.login(userToken);
        } catch (UnknownAccountException e) {
            message = e.getMessage();
        } catch (IncorrectCredentialsException ice) {
            message = "密码不正确";
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            message = "用户名或密码不正确";
        }
        if (currentUser.isAuthenticated()) {
            UserDto userDto = userService.getByUserName(username);
            String token = JWTUtil.sign(userDto.getId(), password, jwtConfig.getIssure());
            //设置用户session
            Session session = currentUser.getSession();
            session.setAttribute("user", userDto);
            session.setAttribute("Authorization", token);
            return WebResult.success();
        } else {
            return new WebResult(message, false);
        }
    }

    @GetMapping(value = "admin/register")
    public String register() {
        return "admin/register";
    }


}
