package com.wyb.test.mybatis.dao;

import com.wyb.test.mybatis.annotation.MyMapper;
import com.wyb.test.mybatis.annotation.MySelect;

/**
 * @author Marcher丶
 */
@MyMapper
public interface UserMapper1 {

    @MySelect("select * from user where id = ${id}")
    String getUserById(Long id);
}