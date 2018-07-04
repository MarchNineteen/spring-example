package com.wyb.shiro.dao.mapper;

import com.wyb.shiro.dao.BaseMapper;
import com.wyb.shiro.dao.model.RoleDo;

import java.util.List;

public interface RoleDoMapper extends BaseMapper<RoleDo> {

    List<RoleDo> getRolesByUserID(Long userId);
}