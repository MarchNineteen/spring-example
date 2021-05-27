package com.wyb.mybatis.multidatasource.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyb.mybatis.multidatasource.dao.mapper.master.MUserMapper;
import com.wyb.mybatis.multidatasource.dao.mapper.master_dt.DtUserMapper;
import com.wyb.mybatis.multidatasource.dao.model.UserDo;
import com.wyb.mybatis.multidatasource.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Marcher丶
 */
@Slf4j
@Service()
public class TransactionServiceImpl implements TransactionService {

    @Resource
    MUserMapper mUserMapper;

    @Resource
    DtUserMapper dtUserMapper;

    /**
     * 老版本必须指定事务 由于使用jta+atomikos解决分布式事务，所以此处不必再指定事务
     *
     * @param userDo
     */
    // @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor =
    // {java.lang.RuntimeException.class})
    @Transactional
    @Override
    public void testDtTransaction(UserDo userDo) {
        log.error("测试分布式事务");
        mUserMapper.insert(userDo);

        dtUserMapper.insert(userDo);

        System.out.println(1 / 0);
        log.error("分布式事务同步成功");
    }

    // @Transactional(transactionManager = "test2TransactionManager", propagation = Propagation.REQUIRED, rollbackFor =
    // { java.lang.RuntimeException.class })
    @Override
    public void testCommonTransaction(UserDo userDo) {
        mUserMapper.insert(userDo);

        System.out.println(1 / 0);
        System.out.println("普通事务同步成功");
    }

}
