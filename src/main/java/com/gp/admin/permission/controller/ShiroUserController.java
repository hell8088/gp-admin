package com.gp.admin.permission.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gp.admin.permission.PremissionConstant;
import com.gp.admin.permission.domain.ShiroUser;
import com.gp.admin.permission.domain.VoShiroUser;
import com.gp.admin.permission.service.ShiroUserService;

/**
 * 
 * @author wangjiehan
 *
 */
@Controller
public class ShiroUserController {

	private static final Logger logger = LoggerFactory.getLogger(ShiroUserController.class);
	private static final String return_list = "user/list";
	private static final String return_edit = "user/edit";
	private static final String redirect_list = "redirect:list";
	private static final String redirect_edit = "redirect:edit";

	@Resource
	private ShiroUserService userService;

	/* --------------- page redirect ---------------- */
	@RequestMapping("/user/edit")
	public ModelAndView edit(int operType, long id) {
		ModelAndView mv = new ModelAndView(return_edit);
		mv.addObject("errMsg", PremissionConstant.noErr);
		mv.addObject("operType", operType);
		mv.addObject("id", id);
		if (id != -1) {
			ShiroUser user = userService.getUserById(id);
			mv.addObject("userName", user.getUserName());
		}
		return mv;
	}

	@RequestMapping("/user/list")
	public ModelAndView list(String menuId) {
		List<ShiroUser> users = userService.getUsers();
		ModelAndView mv = new ModelAndView(return_list);
		mv.addObject("userList", users);
		mv.addObject("menuId", menuId);
		return mv;
	}

	/* --------------- action ---------------- */
	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@RequestMapping("/user/editUser")
	public ModelAndView editUser(VoShiroUser vo, int operType) {
		logger.info("name : " + vo.getUserName());
		ModelAndView mv = null;
		if (vo.getUserName().equals("") || vo.getUserName().equals("")) {
			mv = new ModelAndView(return_edit);
			mv.addObject("errMsg", PremissionConstant.invalidParam);
			return mv;
		}
		boolean flag = true;
		String errMsg = PremissionConstant.noErr;
		try {
			if (operType == 1) {
				userService.addUser(vo, ShiroBaseController.getCurrentUser());
			} else if (operType == 2) {
				userService.updateUser(vo, ShiroBaseController.getCurrentUser());
			} else {
				errMsg = "非法操作";
			}
		} catch (Exception ex) {
			flag = false;
			if (ex instanceof org.springframework.dao.DuplicateKeyException)
				errMsg = " Duplicate key error ! ";
		}

		if (flag) {
			mv = new ModelAndView(redirect_list);
		} else {
			mv = new ModelAndView(return_edit);
			mv.addObject("errMsg", errMsg);
		}
		return mv;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping("/user/del")
	public ModelAndView delUser(long id) {
		userService.delUser(id);
		ModelAndView mv = new ModelAndView(redirect_list);
		return mv;
	}

	/**
	 * 添加用户权限
	 * 
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/user/addRoles")
	public ModelAndView addUserRoles(long userId, String roleIds) {
		String currentUser = ShiroBaseController.getCurrentUser();
		String[] sIds = roleIds.split(",");
		List<Long> ids = new ArrayList<>();
		for (String id : sIds) {
			ids.add(Long.parseLong(id));
		}
		userService.addUserRole(userId, ids, currentUser);
		ModelAndView mv = new ModelAndView(redirect_list);
		return mv;
	}

}
