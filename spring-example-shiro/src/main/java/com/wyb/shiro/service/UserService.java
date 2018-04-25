package com.wyb.shiro.service;

import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.model.UserDo;

/**
 * @author Kunzite
 */
public interface UserService {

    /**
     * 分页获取用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserDo> listByPage(int pageNum, int pageSize);

    /**
     * 根据username获取用户
     *
     * @param userName
     * @return
     */
    UserDo getByUserName(String userName);

    /**
     * 根据id获取用户
     *
     * @param uid
     * @return
     */
    UserDo getById(Integer uid);
}
