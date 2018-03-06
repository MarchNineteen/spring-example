package com.wyb.exception.exception;

/**
 * @author Kunzite
 */
public class NoWxRequest extends Exception {

    public NoWxRequest(){
        super("请在微信浏览器内打开");
    }
}
