package com.wyb.cache;

import com.wyb.cache.config.MemcacheConfig;
import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

	@Resource
    private MemcacheConfig memcacheConfig;

	@Test
	public void testSetGet()  {
		MemcachedClient memcachedClient = memcacheConfig.client();
		memcachedClient.set("testkey",1000,"666666");
		System.out.println("***********  "+memcachedClient.get("testkey").toString());
	}

}
