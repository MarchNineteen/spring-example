package com.wyb.timer.service.impl;


import com.github.pagehelper.PageHelper;
import com.wyb.timer.dao.mapper.UserDoMapper;
import com.wyb.timer.dao.model.UserDo;
import com.wyb.timer.service.UserService;
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
        return userDoMapper.selectAll();
    }
}
