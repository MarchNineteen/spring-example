/*
 * @(#)Main    Created on 2021/3/24
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.thread.apply.hospital;

import com.wyb.thread.pool.threadPoolExecutor.config.Config;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/3/24 16:59 $$
 */
public class Main {

    public static void main(String[] args) {
        NumCenterImpl numCenter = new NumCenterImpl();
        numCenter.init(Config.me());
        for (int i = 0; i < 10; i++) {
            User user = new User(numCenter.getRandomMachine());
            user.getNum();
//            System.out.println("用户取到的号子为" + );
        }
    }
}
