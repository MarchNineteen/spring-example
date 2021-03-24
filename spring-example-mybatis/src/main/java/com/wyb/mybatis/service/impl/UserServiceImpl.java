package com.wyb.mybatis.service.impl;

import com.github.pagehelper.PageInfo;
import com.wyb.mybatis.dao.mapper.UserDoMapper;
import com.wyb.mybatis.dao.model.UserDo;
import com.wyb.mybatis.dao.model.UserExDo;
import com.wyb.mybatis.service.UserExService;
import com.wyb.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author: Marcher丶
 */
@Service
public class UserServiceImpl extends BaseService<UserDo> implements UserService {

    @Resource
    UserDoMapper userDoMapper;
    @Resource
    UserExService userExService;

    @Override
    public PageInfo<UserDo> selectForPage(int pageNum, int pageSize) {
        Example example = new Example(UserDo.class);
        return selectForPage(pageNum, pageSize, example);
    }

    @Override
    public String selectUserNameById(Integer id) {
        return userDoMapper.selectUserNameById(id);
    }

    @Override
    @Transactional
    public void update(UserDo userDo) {
        UserExDo userExDo = new UserExDo();
        userExDo.setId(userDo.getId());
        userExDo.setCountry("user_ex lock");
        userExService.update(userExDo);
        userDoMapper.updateByPrimaryKey(userDo);


    }
}
