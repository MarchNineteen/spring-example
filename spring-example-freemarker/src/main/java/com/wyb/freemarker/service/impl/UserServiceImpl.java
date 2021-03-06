package com.wyb.freemarker.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyb.freemarker.dao.mapper.UserDoMapper;
import com.wyb.freemarker.dao.model.UserDo;
import com.wyb.freemarker.service.UserService;
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
    public List<UserDo> listAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userDoMapper.selectAll();
    }

    @Override
    public UserDo getByName(String username) {
        UserDo userDo = UserDo.builder().username(username).build();
        return userDoMapper.selectOne(userDo);
    }
}
