package com.wyb.freemarker.service.impl;

import com.wyb.freemarker.dao.mapper.UserDoMapper;
import com.wyb.freemarker.dao.model.UserDo;
import com.wyb.freemarker.dao.model.UserDoExample;
import com.wyb.freemarker.service.UserService;
import com.github.pagehelper.PageHelper;
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
    public UserDo getByName(String username) {
        UserDoExample example = new UserDoExample();
        example.createCriteria().andUsernameEqualTo(username);
        return userDoMapper.selectByExample(example).get(0);
    }
}
