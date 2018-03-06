package com.wyb.exception.exception;


/**
 * @author Kunzite
 */
public class NoWxConfException extends Exception {
    public NoWxConfException(){
        super("没有找到微信相关配置");
    }
}
