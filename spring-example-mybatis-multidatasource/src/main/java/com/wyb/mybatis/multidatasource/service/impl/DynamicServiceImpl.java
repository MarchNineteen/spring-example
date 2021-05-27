//package com.wyb.mybatis.multidatasource.service.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.wyb.mybatis.multidatasource.dao.mapper.master.MUserMapper;
//import com.wyb.mybatis.multidatasource.dao.mapper.slave.SUserMapper;
//import com.wyb.mybatis.multidatasource.dao.model.UserDo;
//import com.wyb.mybatis.multidatasource.dynamicdatasource.DataSourceEnum;
//import com.wyb.mybatis.multidatasource.dynamicdatasource.DataSourceSwitcher;
//import com.wyb.mybatis.multidatasource.service.DynamicService;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author Marcher丶
// */
//@Slf4j
//@Service()
//public class DynamicServiceImpl implements DynamicService {
//
//    @Resource
//    MUserMapper mUserMapper;
//    @Resource
//    SUserMapper sUserMapper;
//
//    @DataSourceSwitcher(DataSourceEnum.MASTER)
//    @Override
//    @Transactional(transactionManager = "dataSourceTx", propagation = Propagation.REQUIRED, rollbackFor = {
//            java.lang.RuntimeException.class })
//    public int masterAddUser(UserDo userDo) {
//        int i = mUserMapper.insert(userDo);
//        // System.out.println(1 / 0);
//        return i;
//    }
//
//    @DataSourceSwitcher(DataSourceEnum.MASTER)
//    @Override
//    public List<UserDo> testDataSourceSwitcher() {
//        return sUserMapper.getAll();
//    }
//
//    @DataSourceSwitcher(DataSourceEnum.SLAVE)
//    @Override
//    public List<UserDo> testDataSourceSwitcher1() {
//        return sUserMapper.getAll();
//    }
//}
