package com.wyb.shiro.service;

import com.wyb.shiro.dao.model.MenuDo;

import java.util.List;

/**
 * @author kunzite
 */
public interface MenuService {

    List<MenuDo> getByUserId(Integer id);
}