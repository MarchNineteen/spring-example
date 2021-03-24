package com.wyb.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
