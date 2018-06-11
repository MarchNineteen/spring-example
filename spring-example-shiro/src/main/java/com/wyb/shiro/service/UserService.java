package com.wyb.shiro.service;

import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.dto.UserDto;

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
    PageInfo<UserDto> listByPage(int pageNum, int pageSize);

    /**
     * 根据username获取用户
     *
     * @param userName
     * @return
     */
    UserDto getByUserName(String userName);

    /**
     * 根据id获取用户
     *
     * @param uid
     * @return
     */
    UserDto getById(Long uid);
}
