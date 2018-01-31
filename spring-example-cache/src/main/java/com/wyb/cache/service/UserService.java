package com.wyb.cache.service;


import com.wyb.cache.dao.model.UserDo;

import java.util.List;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date: 2018-01-07 19:22
 */
public interface UserService {
    List<UserDo> listAll(int pageNum, int pageSize);
}
