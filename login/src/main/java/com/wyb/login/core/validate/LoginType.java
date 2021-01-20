package com.wyb.login.core.validate;

import com.wyb.login.core.properties.LoginConstants;

/**
 * @author Marcher丶
 */
public enum LoginType {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return LoginConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return LoginConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    },

    /**
     * 二维码验证码（扫码）
     */
    QR_CODE {
        @Override
        public String getParamNameOnValidate() {
            return null;
        }
    },

    /**
     * 滑块验证
     */
    SLIDE {
        @Override
        public String getParamNameOnValidate() {
            return null;
        }
    };

    public abstract String getParamNameOnValidate();
}
