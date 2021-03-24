/*
 * @(#)UserEx    Created on 2021/3/23
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.mybatis.service;

import com.wyb.mybatis.dao.model.UserExDo;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/3/23 18:59 $$
 */
public interface UserExService extends IService<UserExDo>{
    void update(UserExDo userExDo);
}
