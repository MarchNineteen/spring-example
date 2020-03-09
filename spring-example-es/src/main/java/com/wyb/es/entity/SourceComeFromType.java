/*
 * @(#)SourseType.java    Created on 2016年12月19日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package com.wyb.es.entity;

/**
 * 数据来源（10：PC端，21:安卓手机端，22:苹果手机端, 23:安卓PAD 24:苹果PAD 25：手机浏览器 50：无限宝客户端 51：无限宝手机端 52:无限宝服务端）
 *
 * @author yanjq
 * @version $Revision: 1.0 $, $Date: 2016年12月19日 下午7:30:07 $
 */
public enum SourceComeFromType {
    /** PC端 **/
    PC(10, "PC端"),
    /** 安卓手机端 **/
    ANDROID(21, "安卓手机端"),
    /** 苹果手机端 **/
    IPHONE(22, "苹果手机端"),
    /** 安卓PAD **/
    ANDROID_PAD(23, "安卓PAD"),
    /** 苹果PAD **/
    IPHONE_PAD(24, "苹果PAD"),
    /** 手机浏览器 **/
    MOBILE_BROSWER(25, "手机浏览器"),
    /** 无限宝客户端(6410前仅用于互动答疑) **/
    WXB_PC(50, "无限宝客户端"),
    /** 无限宝手机端(6410前仅用于互动答疑) **/
    WXB_PHONE(51, "无限宝手机端"),
    /** 无限宝服务端(7410添加，用于无限宝课堂测验的练习来源) **/
    WXB_SERVER(52, "无限宝服务端");

    private int value;
    private String name;

    private SourceComeFromType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getNameValue() {
        return this.name;
    }

    /**
     * 根据值得到类型
     *
     * @param value
     * @return
     */
    public static SourceComeFromType get(int value) {
        for (SourceComeFromType type : SourceComeFromType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    /**
     * 根据值得到类型
     *
     * @param value
     * @return 如果没有得到对应的类型，返回null
     */
    public static SourceComeFromType get(String value) {
        for (SourceComeFromType type : SourceComeFromType.values()) {
            if ((String.valueOf(type.getValue())).equals(value)) {
                return type;
            }
        }

        return null;
    }
}
