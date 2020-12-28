package com.wyb.cache.service;


import java.util.List;

import com.wyb.cache.dao.model.UserDo;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date: 2018-01-07 19:22
 */
public interface UserService {
    List<UserDo> listAll(int pageNum, int pageSize);

    UserDo getById(String id);

    UserDo updateUserNameById(UserDo userDo);

    Boolean removeUserById(UserDo userDo);
}
