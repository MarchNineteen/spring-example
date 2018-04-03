package com.wyb.exception.exception;

/**
 * @author Kunzite
 */
public class NoWxOpenIdWException extends Exception {
    public NoWxOpenIdWException() {
        super("暂无获取用户微信信息");
    }

}
