package com.wyb.login.core.properties;

import java.io.Serializable;

/***
 * @author wangyb
 */
public class CapthaProperty implements Serializable {

    private static final long serialVersionUID = -4646601591382805965L;
    /**
     * 滑块底图路径
     */
    private String ORIGINAL_PATH = "captcha.captchaOriginalPath";

    /***
     * 点选底图路径
     */
    private String ORIGINAL_PATH_PIC_CLICK = "captcha.captchaOriginalPath.pic-click";

    /**
     * 缓存local/redis...
     */
    private String CAPTCHA_CACHETYPE = "captcha.cacheType";

    /**
     * 右下角水印文字(我的水印)
     */
    String CAPTCHA_WATER_MARK = "captcha.water.mark";

    /**
     * 点选文字验证码的文字字体(宋体)
     */
    String CAPTCHA_FONT_TYPE = "captcha.font.type";

    /**
     * 验证码类型default两种都实例化。
     */
    String CAPTCHA_TYPE = "captcha.type";

    /**
     * 滑动干扰项(0/1/2)
     */
    Integer CAPTCHA_INTERFERENCE_OPTIONS = 0;

    /**
     * 底图自定义初始化
     */
    Boolean CAPTCHA_INIT_ORIGINAL = false;

    /**
     * 滑动误差偏移量
     */
    String CAPTCHA_SLIP_OFFSET = "captcha.slip.offset";

    /**
     * aes加密开关
     */
    Boolean CAPTCHA_AES_STATUS = true;

    /**
     * 右下角水印字体(宋体)
     */
    String CAPTCHA_WATER_FONT = "captcha.water.font";

    /**
     * local缓存的阈值
     */
    String CAPTCHA_CACAHE_MAX_NUMBER = "captcha.cache.number";

    /**
     * 定时清理过期local缓存，秒
     */
    String CAPTCHA_TIMING_CLEAR_SECOND = "captcha.timing.clear";

    public String getORIGINAL_PATH() {
        return ORIGINAL_PATH;
    }

    public void setORIGINAL_PATH(String ORIGINAL_PATH) {
        this.ORIGINAL_PATH = ORIGINAL_PATH;
    }

    public String getORIGINAL_PATH_PIC_CLICK() {
        return ORIGINAL_PATH_PIC_CLICK;
    }

    public void setORIGINAL_PATH_PIC_CLICK(String ORIGINAL_PATH_PIC_CLICK) {
        this.ORIGINAL_PATH_PIC_CLICK = ORIGINAL_PATH_PIC_CLICK;
    }

    public String getCAPTCHA_CACHETYPE() {
        return CAPTCHA_CACHETYPE;
    }

    public void setCAPTCHA_CACHETYPE(String CAPTCHA_CACHETYPE) {
        this.CAPTCHA_CACHETYPE = CAPTCHA_CACHETYPE;
    }

    public String getCAPTCHA_WATER_MARK() {
        return CAPTCHA_WATER_MARK;
    }

    public void setCAPTCHA_WATER_MARK(String CAPTCHA_WATER_MARK) {
        this.CAPTCHA_WATER_MARK = CAPTCHA_WATER_MARK;
    }

    public String getCAPTCHA_FONT_TYPE() {
        return CAPTCHA_FONT_TYPE;
    }

    public void setCAPTCHA_FONT_TYPE(String CAPTCHA_FONT_TYPE) {
        this.CAPTCHA_FONT_TYPE = CAPTCHA_FONT_TYPE;
    }

    public String getCAPTCHA_TYPE() {
        return CAPTCHA_TYPE;
    }

    public void setCAPTCHA_TYPE(String CAPTCHA_TYPE) {
        this.CAPTCHA_TYPE = CAPTCHA_TYPE;
    }

    public Integer getCAPTCHA_INTERFERENCE_OPTIONS() {
        return CAPTCHA_INTERFERENCE_OPTIONS;
    }

    public void setCAPTCHA_INTERFERENCE_OPTIONS(Integer CAPTCHA_INTERFERENCE_OPTIONS) {
        this.CAPTCHA_INTERFERENCE_OPTIONS = CAPTCHA_INTERFERENCE_OPTIONS;
    }

    public Boolean getCAPTCHA_INIT_ORIGINAL() {
        return CAPTCHA_INIT_ORIGINAL;
    }

    public void setCAPTCHA_INIT_ORIGINAL(Boolean CAPTCHA_INIT_ORIGINAL) {
        this.CAPTCHA_INIT_ORIGINAL = CAPTCHA_INIT_ORIGINAL;
    }

    public String getCAPTCHA_SLIP_OFFSET() {
        return CAPTCHA_SLIP_OFFSET;
    }

    public void setCAPTCHA_SLIP_OFFSET(String CAPTCHA_SLIP_OFFSET) {
        this.CAPTCHA_SLIP_OFFSET = CAPTCHA_SLIP_OFFSET;
    }

    public Boolean getCAPTCHA_AES_STATUS() {
        return CAPTCHA_AES_STATUS;
    }

    public void setCAPTCHA_AES_STATUS(Boolean CAPTCHA_AES_STATUS) {
        this.CAPTCHA_AES_STATUS = CAPTCHA_AES_STATUS;
    }

    public String getCAPTCHA_WATER_FONT() {
        return CAPTCHA_WATER_FONT;
    }

    public void setCAPTCHA_WATER_FONT(String CAPTCHA_WATER_FONT) {
        this.CAPTCHA_WATER_FONT = CAPTCHA_WATER_FONT;
    }

    public String getCAPTCHA_CACAHE_MAX_NUMBER() {
        return CAPTCHA_CACAHE_MAX_NUMBER;
    }

    public void setCAPTCHA_CACAHE_MAX_NUMBER(String CAPTCHA_CACAHE_MAX_NUMBER) {
        this.CAPTCHA_CACAHE_MAX_NUMBER = CAPTCHA_CACAHE_MAX_NUMBER;
    }

    public String getCAPTCHA_TIMING_CLEAR_SECOND() {
        return CAPTCHA_TIMING_CLEAR_SECOND;
    }

    public void setCAPTCHA_TIMING_CLEAR_SECOND(String CAPTCHA_TIMING_CLEAR_SECOND) {
        this.CAPTCHA_TIMING_CLEAR_SECOND = CAPTCHA_TIMING_CLEAR_SECOND;
    }
}
