package com.gp.admin.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author wangjiehan
 *
 */
@RestController
public class IndexController {

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("pages/index");
	}
	
	@RequestMapping("/demo")
	public ModelAndView demo() {
		return new ModelAndView("pages/demo");
	}
	
	@RequestMapping("/userlist")
	public ModelAndView userlist() {
		return new ModelAndView("user/userlist");
	}
	
	
	@RequestMapping(value = "/session")
	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/session");
		mv.addObject("menus", "menus");
		return mv;
	}
	
}
