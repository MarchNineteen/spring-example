package com.wyb.login.core.service.impl;

import com.wyb.login.core.service.CaptchaCacheService;
import com.wyb.login.core.utils.CacheUtil;

/**
 * 验证码的缓存实现 不采用session 或者 cookie 保证安全性
 *
 * @author Marcher丶
 */
public class MemoryCaptchaCacheServiceImpl implements CaptchaCacheService {

    @Override
    public void set(String key, String value, long expiresInSeconds) {

        CacheUtil.set(key, value, expiresInSeconds);
    }

    @Override
    public boolean exists(String key) {
        return CacheUtil.exists(key);
    }

    @Override
    public void delete(String key) {
        CacheUtil.delete(key);
    }

    @Override
    public String get(String key) {
        return CacheUtil.get(key);
    }

    @Override
    public String type() {
        return "local";
    }
}
