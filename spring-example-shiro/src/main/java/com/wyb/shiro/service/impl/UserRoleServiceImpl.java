package com.wyb.shiro.service.impl;

import com.wyb.shiro.dao.mapper.UserRoleDoMapper;
import com.wyb.shiro.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangyb
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleDoMapper userRoleDoMapper;

}
