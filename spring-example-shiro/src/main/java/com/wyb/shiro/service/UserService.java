package com.wyb.shiro.service;

import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.model.UserDo;

public interface UserService {

    PageInfo<UserDo> listByPage(int pageNum, int pageSize);

    UserDo getByUserName(String userName);
}
