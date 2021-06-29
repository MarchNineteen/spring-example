/*
 * @(#)ConfifReader    Created on 2021/6/24
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.test.java.config;

import com.wyb.test.java.common.BaseEntity;

/**
 * @author user
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/6/24 17:23 $$
 */
public class ConfigReader extends AbstractConfigReader {

    @Override
    protected ConfigValueParser<?> getConfigParser(Long key) {
        if (key == 1L) {
            return BaseEntity::parseConfig;
        }
        return null;
    }


    public static void main(String[] args) throws ReadConfigFailException {
        ConfigReader configReader = new ConfigReader();
        BaseEntity entity = configReader.get(1L);
        System.out.println(entity.toString());
    }
}


