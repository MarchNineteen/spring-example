package com.wyb.freemarker.service;


import com.wyb.freemarker.dao.model.UserDo;

import java.util.List;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date: 2018-01-07 19:22
 */
public interface UserService {
    List<UserDo> listAll(int pageNum, int pageSize);

    UserDo getByName(String username);
}
