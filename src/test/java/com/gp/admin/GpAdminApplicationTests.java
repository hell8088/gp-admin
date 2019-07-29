package com.gp.admin;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.gp.admin.biz.event.AnalyticResult;
import com.gp.admin.biz.event.AnalyticSuccessEvent;
import com.gp.admin.common.sftp.SFTPHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpAdminApplicationTests {



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
	


	//@Test
	public void sftpTest() {
		//sftpHelper.exists("\fff");
		// System.out.println(sFTPConfig.getHost());
		// System.out.println(sFTPConfig.getRasPath());
		//SFTPConfig config2 = new SFTPConfig("10.10.10.10", 20000, 3000, "name", "passwd", "config2 : rasPath2");
	}

	//@Test
	public void testEvent() {
		AnalyticResult result = new AnalyticResult();
		result.setResultMsg("分析完成");
		context.publishEvent(new AnalyticSuccessEvent(this, "", result));
	}
	


}
