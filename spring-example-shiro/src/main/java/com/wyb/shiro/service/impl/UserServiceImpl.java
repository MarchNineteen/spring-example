package com.wyb.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.dto.UserDto;
import com.wyb.shiro.dao.mapper.UserDoMapper;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.service.UserService;
import com.wyb.shiro.utils.BeanUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kunzite
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDoMapper userDoMapper;

    @Override
    public PageInfo<UserDto> listByPage(int pageNum, int pageSize) {
        PageInfo<UserDo> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> userDoMapper.selectAll());
        List<UserDto> userDtoList = pageInfo.getList().stream().map(UserDto::new).collect(Collectors.toList());
        // todo 数据处理
        PageInfo<UserDto> page = new PageInfo<>();
        page.setList(userDtoList);
        return page;
    }

    @Override
    public UserDto getByUserName(String userName) {
        Example example = new Example(UserDo.class);
        example.createCriteria().andEqualTo("is_delete", 0).andEqualTo("username", userName);
        return BeanUtil.copyProperties(userDoMapper.selectByExample(example).get(0), UserDto.class);
    }

    @Override
    public UserDto getById(Long uid) {
        Example example = new Example(UserDo.class);
        example.createCriteria().andEqualTo("is_delete", 0).andEqualTo("id", uid);
        return BeanUtil.copyProperties(userDoMapper.selectByExample(example).get(0), UserDto.class);
    }

}
