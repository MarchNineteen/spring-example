package com.wyb.mybatis.service.impl;

import com.github.pagehelper.PageInfo;
import com.wyb.mybatis.dao.model.UserDo;
import com.wyb.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author: Marcher丶
 */
@Service
public class UserServiceImpl extends BaseService<UserDo> implements UserService {

    @Override
    public PageInfo<UserDo> selectForPage(int pageNum, int pageSize) {
        Example example = new Example(UserDo.class);
        return selectForPage(pageNum, pageSize, example);
    }

}
