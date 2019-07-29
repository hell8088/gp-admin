package com.gp.admin.biz.security.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gp.admin.biz.security.domain.SysRole;
import com.gp.admin.biz.security.domain.SysUser;
import com.gp.admin.biz.security.service.SysResourceService;
import com.gp.admin.biz.security.service.SysRoleService;
import com.gp.admin.biz.security.service.SysUserService;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 下午2:04:10
 */

@Controller
public class SysSecurityController {

	private static final Logger logger = LoggerFactory.getLogger(SysSecurityController.class);

	private static final String RESOURCE_LIST = "pages/resource/list";
	private static final String USER_LIST = "pages/user/list";
	private static final String ROLE_LIST = "pages/role/list";

	private static final String ROLE_EDIT = "pages/role/edit";
	private static final String USER_EDIT = "pages/user/edit";

	@Resource
	private SysResourceService resourceService;

	@Resource
	private SysUserService userService;

	@Resource
	private SysRoleService roleService;

	@RequestMapping("/resource/list")
	public ModelAndView resourceList() {
		logger.info("/resource/list");
		ModelAndView mv = new ModelAndView(RESOURCE_LIST);
		return mv;
	}

	@RequestMapping("/user/list")
	public ModelAndView userList() {
		ModelAndView mv = new ModelAndView(USER_LIST);
		return mv;
	}

	@RequestMapping("/user/edit")
	public ModelAndView userEdit(Long id) {
		ModelAndView mv = new ModelAndView(USER_EDIT);
		SysUser user = new SysUser();
		if (id != 0)
			user = userService.getUserById(id);
		mv.addObject("model", user);
		return mv;
	}

	@RequestMapping("/role/list")
	public ModelAndView roleList() {
		ModelAndView mv = new ModelAndView(ROLE_LIST);
		return mv;
	}

	@RequestMapping("/role/edit")
	public ModelAndView roleEdit(Long id) {
		ModelAndView mv = new ModelAndView(ROLE_EDIT);
		SysRole role = new SysRole();
		if (id != 0)
			role = roleService.getRoleById(id);
		mv.addObject("model", role);
		return mv;
	}

}
