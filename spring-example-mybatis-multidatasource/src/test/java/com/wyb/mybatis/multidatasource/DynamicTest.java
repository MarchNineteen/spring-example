//package com.wyb.mybatis.multidatasource;
//
//import java.util.Date;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.wyb.mybatis.multidatasource.dao.model.UserDo;
//import com.wyb.mybatis.multidatasource.service.DynamicService;
//
///**
// * @author Marcher丶
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DynamicTest {
//    @Resource
//    private DynamicService dynamicService;
//
//    @Test
//    public void testSwitch() {
//        // for (int i = 0; i < 2; i++) {
//        // List<UserDo> list = masterSlaveService.testDataSourceSwitcher();
//        // list.forEach(userDo -> System.out.println(userDo.getUsername()));
//        // List<UserDo> list1 = masterSlaveService.testDataSourceSwitcher1();
//        // list1.forEach(userDo -> System.out.println(userDo.getUsername()));
//        // }
//        UserDo user = new UserDo();
//        user.setUsername("move ");
//        user.setPassword("12345611");
//        user.setSex(1);
//        user.setUpdateTime(new Date());
//        user.setCreateTime(new Date());
//        dynamicService.masterAddUser(user);
//    }
//}
