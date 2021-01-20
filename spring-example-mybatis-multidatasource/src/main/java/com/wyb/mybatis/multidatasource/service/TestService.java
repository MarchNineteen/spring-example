package com.wyb.mybatis.multidatasource.service;

import java.util.List;

import com.wyb.mybatis.multidatasource.dao.model.UserDo;

/**
 * @author Marcher丶
 */
public interface TestService {

    void testTransaction(UserDo userDo);

    void testCommonTransaction(UserDo userDo);

    List<UserDo> testDataSourceSwitcher();

    List<UserDo> testDataSourceSwitcher1();
}
