/*
 * @(#)LoginLogController    Created on 2020/3/5
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.es.controller;

import com.wyb.es.dao.LoginLogRepository;
import com.wyb.es.entity.LoginLogDO;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/3/5 14:50 $$
 */
@RestController
@RequestMapping("/loginlog")
@EnableSwagger2
public class LoginLogController {

    @Resource
    LoginLogRepository loginLogRepository;

    /**
     * 1、查  id
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public LoginLogDO getLoginLogDoById(@PathVariable String id) {
        Optional<LoginLogDO> optional = loginLogRepository.findById(id);
        LoginLogDO loginLogDO = new LoginLogDO();
        if (optional.isPresent()) {
            loginLogDO = optional.get();
        }
        return loginLogDO;
    }

    /**
     * 2、查  ++:全文检索（根据整个实体的所有属性，可能结果为0个）
     *
     * @param q
     * @return
     */
    @GetMapping("/select/{q}")
    public List<LoginLogDO> testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<LoginLogDO> searchResult = loginLogRepository.search(builder);
        Iterator<LoginLogDO> iterator = searchResult.iterator();
        List<LoginLogDO> list = new ArrayList<LoginLogDO>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * 3、查   +++：分页、分数、分域（结果一个也不少）
     *
     * @param page
     * @param size
     * @param q
     * @return
     */
    @GetMapping("/{page}/{size}/{q}")
    public List<LoginLogDO> searchCity(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {

        // 分页参数
        Pageable pageable = PageRequest.of(page, size);

        // 分数，并自动按分排序
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("loginAddress", q)),
//                ScoreFunctionBuilders.weightFactorFunction(1000));
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
//                        ScoreFunctionBuilders.weightFactorFunction(1000)) // 权重：name 1000分
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
//                        ScoreFunctionBuilders.weightFactorFunction(100)); // 权重：message 100分

//        QueryBuilder qb=QueryBuilders.boolQuery().must(QueryBuilders.termQuery("loginAddress", q));
        QueryBuilder qb=QueryBuilders.queryStringQuery(q);

        // 分数、分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(qb).build();


        Page<LoginLogDO> searchPageResults = loginLogRepository.search(searchQuery);
        return searchPageResults.getContent();

    }

    /**
     * 4、增
     *
     * @param LoginLogDO
     * @return
     */
    @PostMapping("/insert")
    public LoginLogDO insertLoginLogDo(LoginLogDO LoginLogDO) {
        LoginLogDO logDO = loginLogRepository.save(LoginLogDO);
        return logDO;
    }

    /**
     * 5、删 id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public LoginLogDO insertLoginLogDo(@PathVariable String id) {
        Optional<LoginLogDO> optional = loginLogRepository.findById(id);
        LoginLogDO loginLogDO = new LoginLogDO();
        if (optional.isPresent()) {
            loginLogDO = optional.get();
        }
        loginLogRepository.delete(loginLogDO);
        return loginLogDO;
    }

    /**
     * 6、改
     *
     * @param LoginLogDO
     * @return
     */
    @PutMapping("/update")
    public LoginLogDO updateLoginLogDo(LoginLogDO LoginLogDO) {
        loginLogRepository.save(LoginLogDO);
        return LoginLogDO;
    }

}
