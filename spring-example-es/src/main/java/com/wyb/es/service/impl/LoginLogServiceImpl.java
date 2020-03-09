/*
 * @(#)LoginLogService    Created on 2020/3/5
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.es.service.impl;

import com.wyb.es.dao.LoginLogRepository;
import com.wyb.es.entity.LoginLogDO;
import com.wyb.es.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/3/5 18:32 $$
 */
@Service
@Slf4j
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    LoginLogRepository repository;
    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void updateIn(long oldAgencyId, long updateAgencyId, Object[] userIds) {
        UpdateByQueryRequestBuilder updateByQueryRequestBuilder = UpdateByQueryAction.INSTANCE.newRequestBuilder(elasticsearchTemplate.getClient());
        updateByQueryRequestBuilder.source("loginlog");
        updateByQueryRequestBuilder.filter(QueryBuilders.termQuery("agencyId", oldAgencyId)).
                filter(QueryBuilders.termsQuery("userId", userIds));
        updateByQueryRequestBuilder.script(
                new Script("ctx._source.agencyId=" + updateAgencyId));
        long count = updateByQueryRequestBuilder.get().getUpdated();
        log.info("更新了:" + count + "条");
    }

    @Override
    public void selectMinFiled(String filedName) {

//        MinAggregationBuilder minFiled = AggregationBuilders.min("min_" + filedName).field(filedName);
//
//        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
//        builder.addAggregation(minFiled);
//        SearchQuery query = builder.withSort(SortBuilders.fieldSort("loginTime").order(SortOrder.ASC)).build();
//        Page<LoginLogDO> page = repository.search(query);
//        List<LoginLogDO> logDO = repository.findTop1ByOrderByLoginTimeAsc();
        LoginLogDO logDO = repository.findTop1ByOrderByLoginTimeAsc();
        System.out.println(logDO.toString());
//        log.info("取到了:" + page.getSize() + "条");
    }




    @Override
    public void createIndex(Class<?> clazz) {
        boolean f = elasticsearchTemplate.createIndex(clazz);
        log.info("创建index:" + LoginLogDO.class.getSimpleName() + (f ? " 成功" : "失败"));
    }

    @Override
    public void createIndex(String indexName) {
        boolean f = elasticsearchTemplate.createIndex(indexName);
        log.info("创建index:" + indexName + (f ? " 成功" : "失败"));
    }

    @Override
    public LoginLogDO insertOne(LoginLogDO loginLogDO) {
        loginLogDO = repository.save(loginLogDO);
        return loginLogDO;
    }
}
