/*
 * @(#)LoginLogRepository    Created on 2020/3/5
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.es.dao;

import com.wyb.es.entity.LoginLogDO;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/3/5 10:37 $$
 */
public interface LoginLogRepository extends ElasticsearchRepository<LoginLogDO, String> {

    @Query("{\"match_phrase\":{\"loginAddress\":\"?0\"}}")
    List<LoginLogDO> findByLoginAddressCustom(String keyword);

    long countByLoginAddress(Long userId);

    List<LoginLogDO> findByLoginAddress(String s);

    LoginLogDO findTop1ByOrderByLoginTimeAsc();
}
