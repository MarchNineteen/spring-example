package com.wyb.es.service;

import com.wyb.es.entity.LoginLogDO;

/**
 * @author Marcher丶
 */
public interface LoginLogService {
    void updateIn(long oldAgencyId, long updateAgencyId, Object[] userIds);

    void selectMinFiled(String filedName);

    void createIndex(Class<?> clazz);

    void createIndex(String indexName);

    LoginLogDO insertOne(LoginLogDO loginLogDO);
}
