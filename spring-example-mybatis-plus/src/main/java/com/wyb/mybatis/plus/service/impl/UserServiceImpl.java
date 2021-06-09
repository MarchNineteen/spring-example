package com.wyb.mybatis.plus.service.impl;

import com.wyb.mybatis.plus.entity.User;
import com.wyb.mybatis.plus.mapper.UserMapper;
import com.wyb.mybatis.plus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Marcher丶
 * @since 2021-06-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
