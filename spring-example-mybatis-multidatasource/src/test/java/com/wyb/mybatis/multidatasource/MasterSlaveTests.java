package com.wyb.mybatis.multidatasource;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyb.mybatis.multidatasource.dao.mapper.master.MUserMapper;
import com.wyb.mybatis.multidatasource.dao.mapper.slave.SUserMapper;
import com.wyb.mybatis.multidatasource.dao.model.UserDo;
import com.wyb.mybatis.multidatasource.service.MasterSlaveService;

/**
 * 数据库主从测试
 *
 * @author Marcher丶
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterSlaveTests {

    @Autowired
    private MUserMapper mUserMapper;

    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    private MasterSlaveService masterSlaveService;

    @Test
    public void getUsers() {
        List<UserDo> users = mUserMapper.getAll();
    }

    @Test
    public void getUser() {
        UserDo user1 = mUserMapper.getOne(1L);
        UserDo user2 = sUserMapper.getOne(1L);
        System.out.println(user1.toString());
        System.out.println(user2.toString());
    }

    @Test
    public void save() {
        UserDo user = new UserDo();
        user.setUsername("master useranme");
        user.setPassword("123456");
        user.setSex(1);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        mUserMapper.insert(user);
    }

    @Test
    public void testTransaction() {
        UserDo user = new UserDo();
        user.setUsername("test1 useranme");
        user.setPassword("123456");
        user.setSex(1);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        masterSlaveService.testMasterTransaction(user);
    }
}
