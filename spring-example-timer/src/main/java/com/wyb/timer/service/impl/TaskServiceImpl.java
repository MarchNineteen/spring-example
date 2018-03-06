package com.wyb.timer.service.impl;

import com.wyb.timer.dao.mapper.UserDoMapper;
import com.wyb.timer.dao.model.UserDo;
import com.wyb.timer.service.TaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-01-30 11:41
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private UserDoMapper userDoMapper;

    public void addUser(){
        UserDo userDo = new UserDo();
        userDo.setUsername("user");
        userDo.setPassword("123456");
        userDo.setSex(0);
        userDo.setAge(12);
        userDo.setStatus(0);
        userDo.setPhone("12345678911");
        userDo.setCreateTime(new Date());
        userDo.setUpdateTime(new Date());
        userDoMapper.insertSelective(userDo);
        System.out.println("success");
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void addUser2(){
        UserDo userDo = new UserDo();
        userDo.setUsername("newUser");
        userDo.setPassword("123456");
        userDo.setSex(0);
        userDo.setAge(12);
        userDo.setStatus(0);
        userDo.setPhone("12345678911");
        userDo.setCreateTime(new Date());
        userDo.setUpdateTime(new Date());
        userDoMapper.insertSelective(userDo);
        System.out.println("success");
    }
}
