package com.wyb.exception.exception;

/**
 * @author Kunzite
 */
public class NoAuth extends RuntimeException {
    public NoAuth(){
        super("没有登录");
    }
}
