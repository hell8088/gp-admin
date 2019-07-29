package com.gp.admin.biz.security.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月11日 下午3:40:34
 */

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private final static String LOGIN_PAGE = "login.html";

	@RequestMapping(value = "/login")
	public ModelAndView Login(String username, String password) {
		logger.info("Login Start");

		ModelAndView mv = null;
		if (username == null || username.equals("")) {
			mv = new ModelAndView(LOGIN_PAGE);
			return mv;
		}

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// token.setRememberMe(true);
		Subject loginSubject = SecurityUtils.getSubject();
		String errMsg = null;

		try {
			// 调用 shiro 认证体系，（ shiro 先校验缓存，缓存没有再调用 CustomerRealm
			// doGetAuthenticationInfo 认证）
			loginSubject.login(token);
		} catch (UnknownAccountException e) {
			errMsg = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			errMsg = "用户名/密码错误";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			errMsg = "其他错误：" + e.getMessage(); // 其他错误，比如锁定，如果想单独处理请单独catch处理
		}

		if (errMsg != null) { // 出错了，返回登录页面
			mv = new ModelAndView(LOGIN_PAGE);
			return mv;
		} else { // 登录成功
			// 登陆成功后会跳转至上一跳的地址，，shiro 内部替换successURL后自动执行跳转，代码忽略该 return 语句
			mv = new ModelAndView("redirect:user/list");
			return mv;
		}
	}

}
