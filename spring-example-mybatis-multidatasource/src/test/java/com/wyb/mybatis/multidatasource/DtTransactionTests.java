package com.wyb.mybatis.multidatasource;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyb.mybatis.multidatasource.dao.model.UserDo;
import com.wyb.mybatis.multidatasource.service.TransactionService;

/**
 * 分布式事务测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DtTransactionTests {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testCommonTransaction() {
        UserDo user = new UserDo();
        user.setUsername("test1 useranme");
        user.setPassword("12345611");
        user.setSex(1);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        transactionService.testDtTransaction(user);
    }

}
