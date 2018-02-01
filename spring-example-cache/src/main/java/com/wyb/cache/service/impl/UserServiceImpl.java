package com.wyb.cache.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyb.cache.dao.mapper.UserDoMapper;
import com.wyb.cache.dao.model.UserDo;
import com.wyb.cache.dao.model.UserDoExample;
import com.wyb.cache.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date: 2018-01-07 19:23
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDoMapper userDoMapper;

    @Override
    public List<UserDo> listAll(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        UserDoExample example = new UserDoExample();
        return userDoMapper.selectByExample(example);
    }

    @Override
    public UserDo getById(String id) {
        return userDoMapper.selectByPrimaryKey(Integer.valueOf(id));
    }
}
