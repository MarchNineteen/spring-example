package com.wyb.mybatis.multidatasource;

import com.wyb.mybatis.multidatasource.dao.mapper.test1.User1Mapper;
import com.wyb.mybatis.multidatasource.dao.mapper.test2.User2Mapper;
import com.wyb.mybatis.multidatasource.dao.model.UserDo;
import com.wyb.mybatis.multidatasource.service.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisMultiDataSourceApplicationTests {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Autowired
    private TestServiceImpl testService;

    @Test
    public void getUsers() {
        List<UserDo> users=user1Mapper.getAll();
    }

    @Test
    public void getUser() {
        UserDo user1=user1Mapper.getOne(2L);
        UserDo user2=user2Mapper.getOne(3L);
        System.out.println(user1.toString());
        System.out.println(user2.toString());
    }

    @Test
    public void save() {
        UserDo user = new UserDo();
        user.setUsername("test1 useranme");
        user.setPassword("123456");
        user.setSex(1);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user2Mapper.insert(user);
    }

    @Test
    public void tetsCoommonTras() {
        UserDo user = new UserDo();
        user.setUsername("test1 useranme");
        user.setPassword("12345611");
        user.setSex(1);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        testService.testCommonTransaction(user);
    }

    @Test
    public void tetsTras() {
        UserDo user = new UserDo();
        user.setUsername("test1 useranme");
        user.setPassword("123456");
        user.setSex(1);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        testService.testTransaction(user);
    }

    public void update(UserDo user) {
        user2Mapper.update(user);
    }

    public void delete() {
        Long id = 0L;
        user1Mapper.delete(id);
    }
}
