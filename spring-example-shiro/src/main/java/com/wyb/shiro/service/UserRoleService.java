package com.wyb.shiro.service;

/**
 * @author Kunizte
 */
public interface UserRoleService {

    /**
     * 为用户添加角色
     *
     * @param userId
     * @param roleIds
     */
    int update(Long userId, Long[] roleIds);
}
