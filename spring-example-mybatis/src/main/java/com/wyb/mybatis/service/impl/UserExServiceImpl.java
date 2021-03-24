/*
 * @(#)UserExServiceImpl    Created on 2021/3/23
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.mybatis.service.impl;

import javax.annotation.Resource;

import com.wyb.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wyb.mybatis.dao.mapper.UserExDoMapper;
import com.wyb.mybatis.dao.model.UserDo;
import com.wyb.mybatis.dao.model.UserExDo;
import com.wyb.mybatis.service.UserExService;

import tk.mybatis.mapper.entity.Example;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/3/23 18:59 $$
 */
@Service("userExService")
public class UserExServiceImpl extends BaseService<UserExDo>implements UserExService {

    @Resource
    UserExDoMapper userExDoMapper;
    @Resource
    UserService userService;

    @Transactional
    @Override
    public void update(UserExDo userExDo) {
        userExDoMapper.updateByPrimaryKey(userExDo);

        UserDo userDo = new UserDo();
        userDo.setId(userExDo.getId());
        userDo.setUsername("user lock");
        userService.updateAll(userDo);
    }

    @Override
    public PageInfo<UserExDo> selectForPage(int pageNum, int pageSize) {
        Example example = new Example(UserExDo.class);
        return selectForPage(pageNum, pageSize, example);
    }
}
