/*
 * @(#)Ticket    Created on 2020/7/1
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.test.design.behavioral.strategy.spring;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/7/1 15:45 $$
 */
public class Ticket {

    private String desc;

    public Ticket(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "desc='" + desc + '\'' +
                '}';
    }

}
