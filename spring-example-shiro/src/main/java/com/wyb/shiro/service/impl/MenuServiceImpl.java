package com.wyb.shiro.service.impl;

import com.wyb.shiro.dao.mapper.MenuDoMapper;
import com.wyb.shiro.dao.model.MenuDo;
import com.wyb.shiro.dao.model.RoleDo;
import com.wyb.shiro.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kunzite
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDoMapper menuDoMapper;

    @Override
    public List<MenuDo> getByUserId(Long id) {
        Example example = new Example(MenuDo.class);
        example.createCriteria().andEqualTo("user_id", id);
        return menuDoMapper.selectByExample(example);
    }

    @Override
    public List<MenuDo> getByRoleIds(List<RoleDo> roles) {
        List<Long> roleIds = new ArrayList<>();
        for (RoleDo roleDo : roles) {
            roleIds.add(roleDo.getId());
        }
        log.info("获取角色ID为{}的权限", StringUtils.strip(roleIds.toString(), "[]"));
        return menuDoMapper.getByRoleIds(StringUtils.strip(roleIds.toString(), "[]"));
    }

}
