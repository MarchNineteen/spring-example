package com.wyb.aop;

import com.wyb.aop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AopApplicationTests {

	@Resource(name = "staticProxyUserServiceImpl")
	private UserService userService;

	@Test
	public void contextLoads() {
		userService.listAll(0, 10);
	}

}
