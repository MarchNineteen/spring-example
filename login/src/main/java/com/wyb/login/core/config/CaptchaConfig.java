package com.wyb.login.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.wyb.login.core.properties.CapthaProperty;
import com.wyb.login.core.service.CaptchaCacheService;
import com.wyb.login.core.service.CaptchaService;
import com.wyb.login.core.service.impl.CaptchaServiceFactory;


@Configuration
public class CaptchaConfig {

    @Bean(name = "captchaCacheService")
    public CaptchaCacheService captchaCacheService() {
        //缓存类型redis/local/....
        return CaptchaServiceFactory.getCache("local");
    }

    @Bean
    @DependsOn("captchaCacheService")
    @ConditionalOnMissingBean
    public CaptchaService captchaService() {
        CapthaProperty config = new CapthaProperty();
//        try {
//            try (InputStream input = CaptchaConfig.class.getClassLoader()
//                    .getResourceAsStream("application.properties")) {
//                config.load(input);
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
        //各种参数设置....
        //缓存类型redis/local/....
//        config.put(Const.CAPTCHA_CACHETYPE, "local");
//        config.put(Const.CAPTCHA_TYPE, "default");
//        config.put(Const.CAPTCHA_INTERFERENCE_OPTIONS, "0");
//        config.put(Const.ORIGINAL_PATH_JIGSAW, "");
//        config.put(Const.ORIGINAL_PATH_PIC_CLICK, "");
//        config.put(Const.CAPTCHA_SLIP_OFFSET, "5");
//        config.put(Const.CAPTCHA_AES_STATUS, "true");
//        config.put(Const.CAPTCHA_CACAHE_MAX_NUMBER, "1000");
//        config.put(Const.CAPTCHA_TIMING_CLEAR_SECOND, "180");
//        if ((StringUtils.isNotBlank(config.getProperty(Const.ORIGINAL_PATH_JIGSAW))
//                && config.getProperty(Const.ORIGINAL_PATH_JIGSAW).startsWith("classpath:"))
//                || (StringUtils.isNotBlank(config.getProperty(Const.ORIGINAL_PATH_PIC_CLICK))
//                && config.getProperty(Const.ORIGINAL_PATH_PIC_CLICK).startsWith("classpath:"))) {
//            //自定义resources目录下初始化底图
//            config.put(Const.CAPTCHA_INIT_ORIGINAL, "true");
//            initializeBaseMap(config.getProperty(Const.ORIGINAL_PATH_JIGSAW),
//                    config.getProperty(Const.ORIGINAL_PATH_PIC_CLICK));
//        }

        config.setCAPTCHA_CACHETYPE("local");
        config.setCAPTCHA_TYPE("blockPuzzle");
        config.setCAPTCHA_INTERFERENCE_OPTIONS(0);
        config.setORIGINAL_PATH("");
        config.setCAPTCHA_SLIP_OFFSET("5");
        config.setCAPTCHA_CACHETYPE("local");
        config.setCAPTCHA_INIT_ORIGINAL(false);
        return CaptchaServiceFactory.getInstance(config);
    }

    // private static void initializeBaseMap(String jigsaw, String picClick) {
    // ImageUtils.cacheBootImage(getResourcesImagesFile(jigsaw + "/defaultImages/original/*.png"),
    // getResourcesImagesFile(jigsaw + "/defaultImages/slidingBlock/*.png"),
    // getResourcesImagesFile(picClick + "/*.png"));
    // }
    //
    // public static Map<String, String> getResourcesImagesFile(String path) {
    // Map<String, String> imgMap = new HashMap<>();
    // ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    // try {
    // Resource[] resources = resolver.getResources(path);
    // for (Resource resource : resources) {
    // byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
    // String string = Base64Utils.encodeToString(bytes);
    // String filename = resource.getFilename();
    // imgMap.put(filename, string);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return imgMap;
    // }

}
