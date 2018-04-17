package com.wyb.shiro.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Kunzite
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
