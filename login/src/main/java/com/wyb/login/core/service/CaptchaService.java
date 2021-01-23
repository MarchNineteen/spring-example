/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.wyb.login.core.service;

import com.wyb.login.core.model.CaptchaResponse;
import com.wyb.login.core.model.vo.CaptchaVO;
import com.wyb.login.core.properties.CapthaProperty;

/**
 * 验证码服务接口
 *
 */
public interface CaptchaService {
    /**
     * 配置初始化
     */
    void init(CapthaProperty config);

    /**
     * 获取验证码
     *
     * @param captchaVO
     * @return
     */
    CaptchaResponse get(CaptchaVO captchaVO);

    /**
     * 核对验证码(前端)
     *
     * @param captchaVO
     * @return
     */
    CaptchaResponse check(CaptchaVO captchaVO);

    /**
     * 二次校验验证码(后端)
     *
     * @param captchaVO
     * @return
     */
    CaptchaResponse verification(CaptchaVO captchaVO);

    /***
     * 验证码类型 通过java SPI机制，接入方可自定义实现类，实现新的验证类型
     *
     * @return
     */
    String captchaType();

}
