package com.wyb.es;

import com.wyb.es.dao.LoginLogRepository;
import com.wyb.es.entity.LoginLogDO;
import com.wyb.es.service.LoginLogService;
import org.apache.http.client.utils.DateUtils;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@SpringBootTest
public class ElasticsearchTemplateTest {

    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    public LoginLogRepository loginLogRepository;
    @Autowired
    public LoginLogService loginLogService;

    @Test
    public void testQueryAll() {

        Iterable<LoginLogDO> iterable = loginLogRepository.findAll();
        List<LoginLogDO> list = new ArrayList<>();
        iterable.forEach(list::add);
        list.forEach(v -> System.out.println(v.getLoginAddress()));
    }

    @Test
    public void testQueryOne() {
        Optional<LoginLogDO> logDO = loginLogRepository.findById(String.valueOf(1));
        if (logDO.isPresent()) {
            LoginLogDO model = logDO.get();
            System.out.println(model.getLoginAddress());
        }
    }

    @Test
    public void testLikeQuery() {
//        long time1 = System.currentTimeMillis();
//        List<LoginLogDO> list = loginLogRepository.findByLoginAddressCustom("福");
//        long time2 = System.currentTimeMillis();
//        System.out.println("共" + list.size() + "条数据，耗时" + (time2-time1)/1000 + "秒");
        long time1 = System.currentTimeMillis();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery("福"))
                .build();
        List<LoginLogDO> list = elasticsearchTemplate.queryForList(searchQuery, LoginLogDO.class);
        long time2 = System.currentTimeMillis();
        System.out.println("共" + list.size() + "条数据，耗时" + (time2 - time1) / 1000 + "秒");

    }

    @Test
    public void testAdd() {
        LoginLogDO logDO = new LoginLogDO();
        logDO.setId(1);
        logDO.setAgencyId(1);
        logDO.setUserId(1);
//        logDO.setLoginTime(Date.from(LocalDate.now().minusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        logDO.setLoginAddress("测试地址哦1");
        loginLogRepository.save(logDO);
    }

    @Test
    public void testAdd2() {
        LoginLogDO logDO = new LoginLogDO();
        logDO.setId(2);
        logDO.setAgencyId(2);
        logDO.setUserId(2);
//        logDO.setLoginTime(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        logDO.setLoginAddress("测试地址哦2");
        loginLogRepository.save(logDO);
    }


    @Test
    public void testQuery() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("test-logstash")
                .withQuery(QueryBuilders.matchQuery("login_address", "局域网2"))
                .build();
//        List<Object> list = elasticsearchTemplate.queryForAlias(searchQuery, Object.class);
        List<AliasMetaData> list = elasticsearchTemplate.queryForAlias("test-logstash");
        System.out.println(list);

    }

    @Test
    public void updateIn() {
        loginLogService.updateIn(8, 9, new Object[]{109, 54555278});
    }

    @Test
    public void selectMinFiled() {
        loginLogService.selectMinFiled("loginTime");
    }

    @Test
    public void countFiled() {
        long len = loginLogRepository.countByLoginAddress(1L);
        System.out.println(len);
    }

    @Test
    public void createIndex() {
//        loginLogService.createIndex(LoginLogDO.class);
        loginLogService.createIndex("testindex");
    }
}
