package com.wyb.freemarker.dao.mapper;

import com.wyb.freemarker.dao.model.UserDo;
import com.wyb.freemarker.dao.model.UserDoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDoMapper {
    long countByExample(UserDoExample example);

    int deleteByExample(UserDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    List<UserDo> selectByExample(UserDoExample example);

    UserDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByExample(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}