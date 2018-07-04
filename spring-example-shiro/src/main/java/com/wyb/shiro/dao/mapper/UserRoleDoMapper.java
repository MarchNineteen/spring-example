package com.wyb.shiro.dao.mapper;

import com.wyb.shiro.dao.BaseMapper;
import com.wyb.shiro.dao.model.RoleDo;
import com.wyb.shiro.dao.model.UserRoleDo;

import java.util.List;

public interface UserRoleDoMapper extends BaseMapper<UserRoleDo> {

    List<RoleDo> getRolesByUserID(Long userId);
}