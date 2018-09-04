package com.wyb.shiro.service;

import com.github.pagehelper.PageInfo;
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

    /**
     * 获取所有角色
     *
     * @return
     * @param pageCurrent
     * @param pageSize
     * @param role
     */
    PageInfo<RoleDo> listRole(Integer pageCurrent, Integer pageSize, RoleDo role);

    /**
     * 添加角色
     *
     * @param roleDo
     */
    void saveRole(RoleDo roleDo);
}
