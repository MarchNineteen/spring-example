package com.wyb.log.controller;

/**
 * @author Marcher丶
 */
public class AllLog {

    public static void main(String[] args) {
        JdkLog jdk = new JdkLog();
        jdk.test();

        Log4J2 log4J2 = new Log4J2();
        log4J2.test();

        LogBack logback = new LogBack();
        logback.test();
    }
}
