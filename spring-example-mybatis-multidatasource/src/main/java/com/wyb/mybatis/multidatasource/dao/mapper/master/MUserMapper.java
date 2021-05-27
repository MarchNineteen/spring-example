package com.wyb.mybatis.multidatasource.dao.mapper.master;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.wyb.mybatis.multidatasource.dao.model.UserDo;

@Repository
public interface MUserMapper {

    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<UserDo> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    UserDo getOne(Long id);

    @Insert("INSERT INTO user(username,password,sex,create_time,update_time) VALUES(#{username}, #{password}, #{sex},#{createTime},#{updateTime})")
    int insert(UserDo user);

    @Update("UPDATE user SET username=#{username},phone=#{phone} WHERE id =#{id}")
    int update(UserDo user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    int delete(Long id);


}
