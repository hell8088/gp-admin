package com.gp.admin.biz.security.controller;

import javax.annotation.Resource;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gp.admin.biz.security.domain.SysResource;
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
	private static final String RESOURCE_CHILD = "pages/resource/childedit";
	private static final String RESOURCE_EDIT = "pages/resource/edit";
	private static final String REDIRECT_LIST = "redirect:/resource/list";

	private static final String ROLE_LIST = "pages/role/list";
	private static final String ROLE_EDIT = "pages/role/edit";

	private static final String USER_LIST = "pages/user/list";
	private static final String USER_EDIT = "pages/user/edit";

	@Resource
	private SysResourceService resourceService;

	@Resource
	private SysUserService userService;

	@Resource
	private SysRoleService roleService;
	
	@Resource
	private ShiroFilterFactoryBean shiroFilterFactoryBean;

	@RequestMapping("/resource/list")
	public ModelAndView resourceList() {
		logger.info(RESOURCE_LIST);
		shiroFilterFactoryBean.getFilterChainDefinitionMap().put("urldymic", "premissionDymic");
		System.out.println("zzz : " + shiroFilterFactoryBean.getFilterChainDefinitionMap().get("urldymic"));
		shiroFilterFactoryBean.getFilterChainDefinitionMap().remove("urldymic");
		System.out.println("zzz : " + shiroFilterFactoryBean.getFilterChainDefinitionMap().get("urldymic"));
		
		ModelAndView mv = new ModelAndView(RESOURCE_LIST);
		return mv;
	}

	@RequestMapping("/resource/child")
	public ModelAndView resourceAdd(Long parentId, String parentName, Long rootId) {
		logger.info(RESOURCE_CHILD);
		ModelAndView mv = new ModelAndView(RESOURCE_CHILD);
		mv.addObject("typeList", resourceService.getResourceType());
		mv.addObject("parentId", parentId);
		mv.addObject("rootId", rootId);
		mv.addObject("parentName", parentName);
		return mv;
	}

	@RequestMapping("/resource/edit")
	public ModelAndView resourceEdit(Long id) {
		logger.info(RESOURCE_EDIT);
		ModelAndView mv = new ModelAndView(RESOURCE_EDIT);
		SysResource model = resourceService.getModel(id);
		mv.addObject("typeList", resourceService.getResourceType());
		mv.addObject("model", model);
		return mv;
	}

	@RequestMapping("/resource/update")
	public String resourceUpdate(SysResource model) {
		resourceService.resourceUpdate(model);
		return REDIRECT_LIST;
	}

	@RequestMapping("/resource/add")
	public String resourceAdd(SysResource model) {
		resourceService.resourceAdd(model);
		return REDIRECT_LIST;
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
