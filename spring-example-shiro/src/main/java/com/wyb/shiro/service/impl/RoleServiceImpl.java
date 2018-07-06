package com.wyb.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<RoleDo> listRole(Integer pageCurrent, Integer pageSize) {
        PageInfo<RoleDo> page = PageHelper.startPage(pageCurrent, pageSize).doSelectPageInfo(() -> roleDoMapper.selectAll());
        return page;
    }

    @Override
    public void saveRole(RoleDo roleDo) {
        roleDo.init();
        roleDoMapper.insertSelective(roleDo);
    }

}
