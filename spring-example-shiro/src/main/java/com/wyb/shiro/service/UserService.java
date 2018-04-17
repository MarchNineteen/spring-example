package com.wyb.shiro.service;

import com.wyb.shiro.dao.model.UserDo;

import java.util.List;

public interface UserService {

    List<UserDo> listByPage();
}
