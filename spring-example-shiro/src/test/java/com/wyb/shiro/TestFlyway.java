/*
 * @(#)TestFlyway    Created on 2019/5/16
 * Copyright (c) 2019 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.shiro;

import org.flywaydb.core.Flyway;
import org.junit.Test;

/**
 * @author wangyb
 * @version $$ Revision: 1.0 $$, $$ Date: 2019/5/16 17:57 $$
 */
public class TestFlyway {

    @Test
    public void flyway(){
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource("jdbc:mysql://111.231.85.51:3306/shiro?useLegacyDatetimeCode=false&serverTimezone=Asia/Hong_Kong&useSSL=false", "shiro", "shiroPwd");

        // Start the migration
        flyway.migrate();
    }
}
