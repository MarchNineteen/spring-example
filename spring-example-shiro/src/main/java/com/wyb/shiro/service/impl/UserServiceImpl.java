package com.wyb.shiro.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyb.shiro.dao.dto.UserDto;
import com.wyb.shiro.dao.mapper.UserDoMapper;
import com.wyb.shiro.dao.model.UserDo;
import com.wyb.shiro.help.UserPasswordHelper;
import com.wyb.shiro.service.UserService;
import com.wyb.shiro.utils.BeanUtil;
import org.apache.commons.collections.CollectionUtils;
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

    @Resource
    private UserPasswordHelper userPasswordHelper;

    @Override
    public PageInfo<UserDto> listByPage(int pageNum, int pageSize) {
        Page<UserDo> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userDoMapper.selectAll());
        List<UserDto> userDtoList = page.getResult().stream().map(UserDto::new).collect(Collectors.toList());
        // todo 数据处理
        PageInfo<UserDto> pageInfo = new PageInfo<UserDto>();
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setList(userDtoList);
        return pageInfo;
    }

    @Override
    public UserDto getByUserName(String userName) {
        Example example = new Example(UserDo.class);
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("username", userName);
        List<UserDo> list = userDoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return BeanUtil.copyProperties(list.get(0), UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto getById(Long uid) {
        Example example = new Example(UserDo.class);
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("id", uid);
        return BeanUtil.copyProperties(userDoMapper.selectByExample(example).get(0), UserDto.class);
    }

    @Override
    public int addUser(String userName, String password) {
        UserDo userDo = UserDo.builder()
                .username(userName)
                .address("杭州")
                .phone("12345678971")
                .password(password)
                .build();
        userDo.init();
        userDo.setPassword(userPasswordHelper.encryptPassword(userDo));
        return userDoMapper.insert(userDo);
    }

}
