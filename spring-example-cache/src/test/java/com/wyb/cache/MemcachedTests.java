package com.wyb.cache;

import com.wyb.cache.config.MemcacheConfig;
import com.wyb.cache.constant.CacheType;
import com.wyb.cache.factory.CacheFactory;
import com.wyb.cache.service.CacheService;
import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTests {

	@Resource
    private MemcacheConfig memcacheConfig;
	@Resource
	private CacheFactory cacheFactory;

	@Test
	public void testSetGet()  {
		MemcachedClient memcachedClient = memcacheConfig.client();
		memcachedClient.set("testkey",1000,"666666");
		System.out.println("***********  "+memcachedClient.get("testkey").toString());
	}

	@Test
	public void testRedisSet()  {
		CacheService cacheService = cacheFactory.getCache(CacheType.REDIS);
		cacheService.putCache("11", "11 value");
		System.out.println(cacheService.getCache("11"));
	}

}
