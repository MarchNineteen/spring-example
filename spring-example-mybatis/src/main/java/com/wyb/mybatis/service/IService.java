package com.wyb.mybatis.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Marcher丶
 */
@Service
public interface IService<T> {

    Integer getNextId(String tableSchema, String tableName);

    List<T> selectAll();

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);

    PageInfo<T> selectForPage(int pageNum, int pageSize);

    PageInfo<T> selectForPage(int pageNum, int pageSize, Example example);
}
