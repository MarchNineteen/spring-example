package com.wyb.login.core.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wyb.login.core.properties.CapthaProperty;
import com.wyb.login.core.service.CaptchaService;
import com.wyb.login.core.utils.AESUtil;
import com.wyb.login.core.utils.ImageUtils;

/**
 * @author Marcher丶
 */
public abstract class AbstractCaptchaService implements CaptchaService {

    private static Logger logger = LoggerFactory.getLogger(AbstractCaptchaService.class);
    protected static final String IMAGE_TYPE_PNG = "png";
    // check校验坐标
    protected static String REDIS_CAPTCHA_KEY = "RUNNING:CAPTCHA:%s";

    // 后台二次校验坐标
    protected static String REDIS_SECOND_CAPTCHA_KEY = "RUNNING:CAPTCHA:second-%s";

    protected static Long EXPIRESIN_SECONDS = 2 * 60L;

    protected static Long EXPIRESIN_THREE = 3 * 60L;

    protected static String slipOffset = "5";

    protected static Boolean captchaAesStatus = true;

    protected static String cacheType = "local";

    protected static int captchaInterferenceOptions = 0;

    /**
     * 滑块拼图图片地址
     */
    private String jigsawUrlOrPath;

    /**
     * 判断应用是否实现了自定义缓存，没有就使用内存
     *
     * @param property
     */
    @Override
    public void init(CapthaProperty property) {
        // 默认图片
        if (!property.getCAPTCHA_INIT_ORIGINAL()) {
            ImageUtils.cacheImage(property.getORIGINAL_PATH());
        }
        logger.info("--->>>初始化验证码底图<<<---");
        slipOffset = StringUtils.isNotEmpty(property.getCAPTCHA_SLIP_OFFSET()) ? property.getCAPTCHA_SLIP_OFFSET()
                : "5";
        captchaAesStatus = property.getCAPTCHA_AES_STATUS();
        cacheType = StringUtils.isNotEmpty(property.getCAPTCHA_CACHETYPE()) ? property.getCAPTCHA_CACHETYPE() : "local";
        captchaInterferenceOptions = property.getCAPTCHA_INTERFERENCE_OPTIONS();
        if ("local".equals(cacheType)) {
            logger.info("初始化local缓存...");
            // CacheUtil.init(Integer.parseInt(config.getProperty(Const.CAPTCHA_CACAHE_MAX_NUMBER, "1000")),
            // Long.parseLong(config.getProperty(Const.CAPTCHA_TIMING_CLEAR_SECOND, "180")));
        }

    }

    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // 解密
            byte[] b = decoder.decode(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 解密前端坐标aes加密
     *
     * @param point
     * @return
     * @throws Exception
     */
    public static String decrypt(String point, String key) throws Exception {
        return AESUtil.aesDecrypt(point, key);
    }

    public String getJigsawUrlOrPath() {
        return jigsawUrlOrPath;
    }

    public void setJigsawUrlOrPath(String jigsawUrlOrPath) {
        this.jigsawUrlOrPath = jigsawUrlOrPath;
    }

}
