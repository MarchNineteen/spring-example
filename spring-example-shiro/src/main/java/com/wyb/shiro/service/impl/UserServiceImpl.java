package com.wyb.shiro.service.impl;

import com.wyb.shiro.dao.mapper.UserDoMapper;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kunzite
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDoMapper userDoMapper;

    @Override
    public List<UserDo> listByPage(){
//        Example example = new Example(UserDo.class);
//        PageHelper.startPage(0,20);
        return userDoMapper.selectAll();
    }

}
