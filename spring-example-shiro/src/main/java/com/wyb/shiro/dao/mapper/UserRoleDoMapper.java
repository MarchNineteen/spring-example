package com.wyb.shiro.dao.mapper;

import com.wyb.shiro.dao.BaseMapper;
import com.wyb.shiro.dao.model.UserRoleDo;
import org.apache.ibatis.annotations.Param;

public interface UserRoleDoMapper extends BaseMapper<UserRoleDo> {

//    List<RoleDo> getRolesByUserID(Long userId);

    void deleteRoleByUserId(@Param("userId") Long userId);

    int insertBatch(@Param("userId") Long userId,@Param("roleIds") Long[] roleIds);
}