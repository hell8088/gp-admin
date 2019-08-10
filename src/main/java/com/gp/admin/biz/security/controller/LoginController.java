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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gp.admin.biz.security.domain.SysUser;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月11日 下午3:40:34
 */

@Controller
public class LoginController {

	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	private final static String LOGIN_PAGE = "login.html";
	private final static String INDEX_PAGE = "/user/list";

	
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/test")
	public ModelAndView test() {
		return new ModelAndView("pages/test/test");
	}
	
	@RequestMapping(value = "/401error")
	public ModelAndView _401Err() {
		return new ModelAndView("/401.html");
	}

	
	/**
	 * 跳转到login页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getPrincipal();
		if (user == null) {
			return reLoginPage();
		} else {
			return reIndexPage();
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String username, String password) {
		logger.info("Login Start");
		if (username == null || username.equals("")) {
			return reLoginPage();
		}

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// token.setRememberMe(true);
		Subject loginSubject = SecurityUtils.getSubject();
		String errMsg = null;
		try {
			// 调用 shiro 认证体系，（ shiro 先校验缓存，缓存没有再调用CustomerRealm
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

		if (errMsg != null)
			return reLoginPage();
		else
			return reIndexPage(); // post请求会跳转refer，get请求会走return
	}

	/**
	 * 
	 * @return
	 */
	private ModelAndView reLoginPage() {
		return new ModelAndView(LOGIN_PAGE);
	}

	private ModelAndView reIndexPage() {
		return new ModelAndView("redirect:" + INDEX_PAGE);
	}

}
