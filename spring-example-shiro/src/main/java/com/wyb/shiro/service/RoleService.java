package com.wyb.shiro.service;

import com.wyb.shiro.dao.model.RoleDo;

import java.util.List;

public interface RoleService {
    /**
     * 根据用户id获取用户角色
     *
     * @param userId
     * @return
     */
    List<RoleDo> getRolesByUserID(Long userId);
}
