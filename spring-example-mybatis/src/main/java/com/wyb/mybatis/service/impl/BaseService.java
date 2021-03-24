package com.wyb.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyb.mybatis.dao.BaseMapper;
import com.wyb.mybatis.service.IService;

import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Marcher丶
 */
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected BaseMapper<T> mapper;

    public BaseMapper<T> getMapper() {
        return mapper;
    }

    @Override
    public Integer getNextId(String tableSchema, String tableName) {
        return mapper.getNextId(tableSchema, tableName);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int updateAll(T entity) {
        // 说明：根据主键更新实体全部字段，null值会被更新
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        // 根据主键更新属性不为null的值
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        // 说明：根据Example条件进行查询
        // 重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
        return mapper.selectByExample(example);
    }

    @Override
    public PageInfo<T> selectForPage(int pageNum, int pageSize, Example example) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> mapper.selectByExample(example));
    }
}
