/*
 * @(#)LoginLogService    Created on 2020/3/6
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.es.service;

import com.wyb.es.entity.LoginLogDO;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/3/6 9:57 $$
 */
public interface LoginLogService {
    void updateIn(long oldAgencyId, long updateAgencyId, Object[] userIds);

    void selectMinFiled(String filedName);

    void createIndex(Class<?> clazz);

    void createIndex(String indexName);

    LoginLogDO insertOne(LoginLogDO loginLogDO);
}
