/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.wyb.login.core.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSONObject;
import com.wyb.login.core.model.CaptchaBaseMapEnum;

public class ImageUtils {
    private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    private static Map<String, String> originalCacheMap = new ConcurrentHashMap(); // 滑块底图
    private static Map<String, String> slidingBlockCacheMap = new ConcurrentHashMap(); // 滑块
    private static Map<String, String[]> fileNameMap = new ConcurrentHashMap<>();

    /**
     * 默认图片加载
     *
     * @param captchaOriginalPath
     */
    public static void cacheImage(String captchaOriginalPath) {
        // 滑动拼图
        if (StringUtils.isBlank(captchaOriginalPath)) {
            originalCacheMap.putAll(getResourcesImagesFile("defaultImages/original"));
            slidingBlockCacheMap.putAll(getResourcesImagesFile("defaultImages/slidingBlock"));
        }
        else {
            originalCacheMap.putAll(getImagesFile(captchaOriginalPath + File.separator + "original"));
            slidingBlockCacheMap
                    .putAll(getImagesFile(captchaOriginalPath + File.separator + "defaultImages/slidingBlock"));
        }
        fileNameMap.put(CaptchaBaseMapEnum.ORIGINAL.getCodeValue(), originalCacheMap.keySet().toArray(new String[0]));
        fileNameMap.put(CaptchaBaseMapEnum.SLIDING_BLOCK.getCodeValue(),
                slidingBlockCacheMap.keySet().toArray(new String[0]));
        logger.info("初始化底图:{}", JSONObject.toJSONString(fileNameMap));
    }

    /**
     * 自定义图片加载
     *
     * @param originalMap
     * @param slidingBlockMap
     */
    public static void cacheBootImage(Map<String, String> originalMap, Map<String, String> slidingBlockMap) {
        originalCacheMap.putAll(originalMap);
        slidingBlockCacheMap.putAll(slidingBlockMap);
        fileNameMap.put(CaptchaBaseMapEnum.ORIGINAL.getCodeValue(), originalCacheMap.keySet().toArray(new String[0]));
        fileNameMap.put(CaptchaBaseMapEnum.SLIDING_BLOCK.getCodeValue(),
                slidingBlockCacheMap.keySet().toArray(new String[0]));
        logger.info("自定义resource底图:{}", JSONObject.toJSONString(fileNameMap));
    }

    /**
     * 获取原图
     *
     * @return
     */
    public static BufferedImage getOriginal() {
        String[] strings = fileNameMap.get(CaptchaBaseMapEnum.ORIGINAL.getCodeValue());
        if (null == strings || strings.length == 0) {
            return null;
        }
        Integer randomInt = RandomUtils.getRandomInt(0, strings.length);
        String s = originalCacheMap.get(strings[randomInt]);
        return getBase64StrToImage(s);
    }

    /**
     * 获取滑块
     *
     * @return
     */
    public static String getSlidingBlock() {
        String[] strings = fileNameMap.get(CaptchaBaseMapEnum.SLIDING_BLOCK.getCodeValue());
        if (null == strings || strings.length == 0) {
            return null;
        }
        Integer randomInt = RandomUtils.getRandomInt(0, strings.length);
        return slidingBlockCacheMap.get(strings[randomInt]);
    }

    private static Map<String, String> getResourcesImagesFile(String path) {
        // 默认提供六张底图
        Map<String, String> imgMap = new HashMap<>();
        ClassLoader classLoader = ImageUtils.class.getClassLoader();
        for (int i = 1; i <= 6; i++) {
            InputStream resourceAsStream = classLoader
                    .getResourceAsStream(path.concat("/").concat(String.valueOf(i).concat(".png")));
            byte[] bytes = new byte[0];
            try {
                bytes = FileCopyUtils.copyToByteArray(resourceAsStream);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            String string = Base64Utils.encodeToString(bytes);
            String filename = String.valueOf(i).concat(".png");
            imgMap.put(filename, string);
        }
        return imgMap;
    }

    private static Map<String, String> getImagesFile(String path) {
        Map<String, String> imgMap = new HashMap<>();
        File file = new File(path);
        if (!file.exists()) {
            return new HashMap<>();
        }
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(item -> {
            try {
                FileInputStream fileInputStream = new FileInputStream(item);
                byte[] bytes = FileCopyUtils.copyToByteArray(fileInputStream);
                String string = Base64Utils.encodeToString(bytes);
                imgMap.put(item.getName(), string);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        return imgMap;
    }

    /**
     * 图片转base64 字符串
     *
     * @param templateImage
     * @return
     */
    public static String getImageToBase64Str(BufferedImage templateImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(templateImage, "png", baos);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();

        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(bytes).trim();
    }

    /**
     * base64 字符串转图片
     *
     * @param base64String
     * @return
     */
    public static BufferedImage getBase64StrToImage(String base64String) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(base64String);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            return ImageIO.read(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
