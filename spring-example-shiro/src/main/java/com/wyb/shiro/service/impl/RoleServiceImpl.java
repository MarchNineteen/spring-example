package com.wyb.shiro.service.impl;

import com.wyb.shiro.dao.mapper.RoleDoMapper;
import com.wyb.shiro.dao.model.RoleDo;
import com.wyb.shiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyb
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDoMapper roleDoMapper;

    @Override
    public List<RoleDo> getRolesByUserID(Long userId) {
        return roleDoMapper.getRolesByUserID(userId);
    }

}
