package com.gp.admin;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.gp.admin.event.AnalyticResult;
import com.gp.admin.event.AnalyticSuccessEvent;
import com.gp.admin.permission.domain.ShiroUser;
import com.gp.admin.permission.service.ShiroUserService;

import com.gp.admin.sftp.SFTPHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpAdminApplicationTests {

	@Resource
	private ShiroUserService userService;

	@Resource
	private SFTPHelper sftpHelper;

	@Resource
	ApplicationContext context;

	@Before
	public void start(){
		System.out.println("zzz 测试开始!");
	}
	
	@After
	public void end(){
		System.out.println("zzz 测试结束!");
	}
	
	// @Test
	public void getUsers() {
		List<ShiroUser> userList = userService.getUsers();
		for (ShiroUser shiroUser : userList) {
			System.out.println(shiroUser.getUserName());
			System.out.println(shiroUser.getPassword());
		}
	}

	//@Test
	public void sftpTest() {
		//sftpHelper.exists("\fff");
		// System.out.println(sFTPConfig.getHost());
		// System.out.println(sFTPConfig.getRasPath());
		//SFTPConfig config2 = new SFTPConfig("10.10.10.10", 20000, 3000, "name", "passwd", "config2 : rasPath2");
	}

	@Test
	public void testEvent() {
		AnalyticResult result = new AnalyticResult();
		result.setResultMsg("分析完成");
		context.publishEvent(new AnalyticSuccessEvent(this, "", result));
	}

}
