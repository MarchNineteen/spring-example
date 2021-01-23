/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.wyb.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyb.login.core.model.CaptchaResponse;
import com.wyb.login.core.model.vo.CaptchaVO;
import com.wyb.login.core.service.CaptchaService;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/get")
    public CaptchaResponse get(@RequestBody CaptchaVO captchaVO) {
        return captchaService.get(captchaVO);
    }

    @PostMapping("/check")
    public CaptchaResponse check(@RequestBody CaptchaVO captchaVO) {
        return captchaService.check(captchaVO);
    }

    @PostMapping("/verify")
    public CaptchaResponse verify(@RequestBody CaptchaVO captchaVO) {
        return captchaService.verification(captchaVO);
    }

}
