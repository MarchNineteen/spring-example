package com.wyb.mybatis.multidatasource.service;

import com.wyb.mybatis.multidatasource.dao.mapper.test1.User1Mapper;
import com.wyb.mybatis.multidatasource.dao.mapper.test2.User2Mapper;
import com.wyb.mybatis.multidatasource.dao.model.UserDo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Marcher丶
 */
@Service
public class TestServiceImpl {

    @Resource
    User1Mapper user1Mapper;

    @Resource
    User2Mapper user2Mapper;

    /**
     * 老版本必须指定事务
     * 由于使用jta+atomikos解决分布式事务，所以此处不必再指定事务
     *
     * @param userDo
     */
//    @Transactional(transactionManager = "xatx", propagation = Propagation.REQUIRED, rollbackFor = {java.lang.RuntimeException.class})
    @Transactional
    public void testTransaction(UserDo userDo) {
        user2Mapper.insert(userDo);

        user1Mapper.insert(userDo);

        System.out.println(1 / 0);
        System.out.println("分布式事务同步成功");
    }

    //    @Transactional(transactionManager = "test2TransactionManager", propagation = Propagation.REQUIRED, rollbackFor = { java.lang.RuntimeException.class })
    public void testCommonTransaction(UserDo userDo) {
        user2Mapper.insert(userDo);

        System.out.println(1 / 0);
        System.out.println("普通事务同步成功");
    }
}
