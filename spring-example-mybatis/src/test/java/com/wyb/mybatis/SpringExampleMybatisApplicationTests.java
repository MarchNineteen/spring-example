package com.wyb.mybatis;

import com.github.pagehelper.PageInfo;
import com.wyb.mybatis.dao.model.UserDo;
import com.wyb.mybatis.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringExampleMybatisApplicationTests {

    @Resource
    UserService userService;

    @Test
    public void testAddUser() {
        UserDo user = new UserDo();
        user.setId(userService.getNextId("springboot", "user"));
        user.setUsername("scott");
        user.setPassword("ac089b11709f9b9e9980e7c497268dfa");
        user.setPhone("12321");
        user.setSex(1);
        user.setAge(12);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(0);
        Assert.assertEquals(1, userService.save(user));
    }

    @Test
    public void testSelectForPage() {
        PageInfo<UserDo> pageInfo = userService.selectForPage(1, 10);
    }

}
