package com.wyb.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

    @Select("select auto_increment from information_schema.`tables` where table_name=#{tableName} and table_schema=#{tableSchema}")
    Integer getNextId(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
}
