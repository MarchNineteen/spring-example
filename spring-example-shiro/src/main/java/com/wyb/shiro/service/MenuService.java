package com.wyb.shiro.service;

import com.wyb.shiro.dao.model.MenuDo;
import com.wyb.shiro.dao.model.RoleDo;

import java.util.List;

/**
 * @author Kunzite
 */
public interface MenuService {

    List<MenuDo> getByUserId(Long id);

    List<MenuDo> getByRoleIds(List<RoleDo> roles);
}
