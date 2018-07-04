package com.wyb.shiro.dao.mapper;

import com.wyb.shiro.dao.BaseMapper;
import com.wyb.shiro.dao.model.MenuDo;

import java.util.List;

/**
 * @author Kunzite
 */
public interface MenuDoMapper extends BaseMapper<MenuDo> {

    List<MenuDo> getByRoleIds(String s);
}