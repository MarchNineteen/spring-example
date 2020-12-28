package com.wyb.mybatis.service.impl;

import com.github.pagehelper.PageInfo;
import com.wyb.mybatis.dao.mapper.UserDoMapper;
import com.wyb.mybatis.dao.model.UserDo;
import com.wyb.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author: Marcher丶
 */
@Service
public class UserServiceImpl extends BaseService<UserDo> implements UserService {

    @Resource
    UserDoMapper userDoMapper;

    @Override
    public PageInfo<UserDo> selectForPage(int pageNum, int pageSize) {
        Example example = new Example(UserDo.class);
        return selectForPage(pageNum, pageSize, example);
    }

    @Override
    public String selectUserNameById(Integer id) {
        return userDoMapper.selectUserNameById(id);
    }
}
