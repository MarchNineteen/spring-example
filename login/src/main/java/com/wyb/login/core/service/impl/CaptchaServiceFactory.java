package com.wyb.login.core.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.apache.commons.lang3.StringUtils;

import com.wyb.login.core.properties.CapthaProperty;
import com.wyb.login.core.service.CaptchaCacheService;
import com.wyb.login.core.service.CaptchaService;

import lombok.extern.slf4j.Slf4j;

/**
 * 通过配置参数获取captcha和captchaService
 *
 * @author Marcher丶
 */
@Slf4j
public class CaptchaServiceFactory {

    public volatile static Map<String, CaptchaService> instances = new HashMap();
    public volatile static Map<String, CaptchaCacheService> cacheService = new HashMap();

    public static CaptchaCacheService getCache(String cacheType) {
        return cacheService.get(cacheType);
    }

    static {
        ServiceLoader<CaptchaCacheService> cacheServices = ServiceLoader.load(CaptchaCacheService.class);
        for (CaptchaCacheService item : cacheServices) {
            cacheService.put(item.type(), item);
        }
        log.info("supported-captchaCache-service:{}", cacheService.keySet().toString());
        ServiceLoader<CaptchaService> services = ServiceLoader.load(CaptchaService.class);
        for (CaptchaService item : services) {
            instances.put(item.captchaType(), item);
        }
        log.info("supported-captchaTypes-service:{}", instances.keySet().toString());
    }

    public static CaptchaService getInstance(CapthaProperty config) {
        CaptchaService ret = instances
                .get(StringUtils.isNotEmpty(config.getCAPTCHA_TYPE()) ? config.getCAPTCHA_TYPE() : "default");
        if (ret == null) {
            throw new RuntimeException("unsupported-[captcha.type]=" + config.getCAPTCHA_CACHETYPE());
        }
        ret.init(config);
        return ret;
    }
}
