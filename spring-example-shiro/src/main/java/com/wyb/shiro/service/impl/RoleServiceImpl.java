package com.wyb.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.mapper.RoleDoMapper;
import com.wyb.shiro.dao.model.RoleDo;
import com.wyb.shiro.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public PageInfo<RoleDo> listRole(Integer pageCurrent, Integer pageSize, RoleDo role) {
        Example example = new Example(RoleDo.class);
        if (StringUtils.isNotEmpty(role.getRoleName())) {
            example.createCriteria().andLike("roleName", "%" + role.getRoleName() + "%");
        }
        example.setOrderByClause("create_time DESC");
        PageInfo<RoleDo> page = PageHelper.startPage(pageCurrent, pageSize).doSelectPageInfo(() -> roleDoMapper.selectByExample(example));
        return page;
    }

    @Override
    public void saveRole(RoleDo roleDo) {
        roleDo.init();
        roleDoMapper.insertSelective(roleDo);
    }

}
