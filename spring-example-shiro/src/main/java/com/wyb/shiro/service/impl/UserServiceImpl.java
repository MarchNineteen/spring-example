package com.wyb.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.mapper.UserDoMapper;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kunzite
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDoMapper userDoMapper;

    @Override
    public PageInfo<UserDo> listByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UserDo> page = new PageInfo();
        page.setList(userDoMapper.selectAll());
        return page;
    }

    @Override
    public UserDo getByUserName(String userName) {
        UserDo userDo = new UserDo();
        userDo.setUsername(userName);
        userDo.setIsDelete(0);
        return userDoMapper.selectOne(userDo);
    }

}
