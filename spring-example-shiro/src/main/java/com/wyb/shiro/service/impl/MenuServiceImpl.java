package com.wyb.shiro.service.impl;

import com.wyb.shiro.dao.mapper.MenuDoMapper;
import com.wyb.shiro.dao.model.MenuDo;
import com.wyb.shiro.service.MenuService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kunzite
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDoMapper menuDoMapper;

    @Override
    public List<MenuDo> getByUserId(Integer id) {
        Example example = new Example(MenuDo.class);
        example.createCriteria().andEqualTo("user_id",id);
        return menuDoMapper.selectByExample(example);
    }
}
