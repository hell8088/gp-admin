package com.gp.admin;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gp.admin.permission.domain.ShiroUser;
import com.gp.admin.permission.service.ShiroUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpAdminApplicationTests {

	@Resource
	private ShiroUserService userService;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getUsers(){
		List<ShiroUser> userList = userService.getUsers();
		for (ShiroUser shiroUser : userList) {
			System.out.println(shiroUser.getUserName());
			System.out.println(shiroUser.getPassword());
		}
	}

}
