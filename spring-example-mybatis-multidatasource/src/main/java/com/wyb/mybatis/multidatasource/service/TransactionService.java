package com.wyb.mybatis.multidatasource.service;

import com.wyb.mybatis.multidatasource.dao.model.UserDo;

/**
 * @author Marcher丶
 */
public interface TransactionService {

    void testDtTransaction(UserDo userDo);

    void testCommonTransaction(UserDo userDo);

}
