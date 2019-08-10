package com.gp.admin;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.gp.admin.biz.event.ResourceEvent;
import com.gp.admin.common.sftp.SFTPHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpAdminApplicationTests {

	@Resource
	private SFTPHelper sftpHelper;

	@Resource
	ApplicationContext context;

//	@Resource
//	StringRedisTemplate stringRedisTemplate;

	@Resource
	RedisTemplate<String, Object> redisTemplate;

	@Before
	public void start() {
		System.out.println("zzz 测试开始!");
	}

	@After
	public void end() {
		System.out.println("zzz 测试结束!");
	}

	// @Test
	public void sftpTest() {
		// sftpHelper.exists("\fff");
		// System.out.println(sFTPConfig.getHost());
		// System.out.println(sFTPConfig.getRasPath());
		// SFTPConfig config2 = new SFTPConfig("10.10.10.10", 20000, 3000,
		// "name", "passwd", "config2 : rasPath2");
	}

	//@Test
	public void redisTest() {
		System.out.println("zzz redis test");
		System.out.println(redisTemplate.keys("*").size());
		redisTemplate.opsForValue().set("gpadmin:test1", "gp-admin redis test value");
		System.out.println(redisTemplate.opsForValue().get("gpadmin:test1"));
	}

	@Test
	public void testEvent() {
		ResourceEvent resEvent = new ResourceEvent(this,"resourceUrl");
		context.publishEvent(resEvent);
	}

}
