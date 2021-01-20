package com.wyb.mybatis.multidatasource.dynamic;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyb.mybatis.multidatasource.dao.model.UserDo;
import com.wyb.mybatis.multidatasource.service.TestService;

/**
 * @author Marcher丶
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicTest {
    @Resource
    private TestService testService;

    @Test
    public void testSwitch() {
        for (int i = 0; i < 2; i++) {
            List<UserDo> list = testService.testDataSourceSwitcher();
            list.forEach(userDo -> System.out.println(userDo.getUsername()));
            List<UserDo> list1 = testService.testDataSourceSwitcher1();
            list1.forEach(userDo -> System.out.println(userDo.getUsername()));
        }

    }
}
