package com.gp.admin.permission.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gp.admin.permission.PremissionConstant;
import com.gp.admin.permission.domain.ShiroRole;
import com.gp.admin.permission.domain.VoShiroRole;
import com.gp.admin.permission.service.ShiroRoleService;

/**
 * 
 * @author wangjiehan
 *
 */
@Controller
public class ShiroRoleController {

	private static final Logger logger = LoggerFactory.getLogger(ShiroRoleController.class);
	private static final String return_list = "role/list";
	private static final String return_edit = "role/edit";
	private static final String return_rolealloc = "role/rolealloc";
	private static final String redirect_list = "redirect:list";
	private static final String redirect_edit = "redirect:edit";

	@Resource
	private ShiroRoleService roleService;

	/* --------------- page redirect ---------------- */
	@RequestMapping("/role/edit")
	public ModelAndView edit(int operType, long id) {
		ModelAndView mv = new ModelAndView(return_edit);
		mv.addObject("errMsg", PremissionConstant.noErr);
		mv.addObject("operType", operType);
		mv.addObject("id", id);
		if (id != -1) {
			ShiroRole role = roleService.getRoleById(id);
			mv.addObject("roleName", role.getRoleName());
			mv.addObject("roleDesc", role.getRoleDesc());
		}
		return mv;
	}

	@RequestMapping("/role/list")
	public ModelAndView list(String menuId) {
		List<ShiroRole> roles = roleService.getRoles();
		ModelAndView mv = new ModelAndView(return_list);
		mv.addObject("roleList", roles);
		mv.addObject("menuId", menuId);
		return mv;
	}

	// 用户角色关系列表
	@RequestMapping("/role/rolealloc")
	public ModelAndView userRole(long userId) {
		List<ShiroRole> roles = roleService.getRoles();		//获取所有role
		List<Long> ids = roleService.getRoleIds(userId);	//获取已选中角色 ID 集合
		StringBuilder idBuilder = new StringBuilder();
		for (long id : ids) {
			idBuilder.append(id).append(",");
		}
		if (idBuilder.length() > 0)
			idBuilder.deleteCharAt(idBuilder.length() - 1);

		ModelAndView mv = new ModelAndView(return_rolealloc);
		mv.addObject("roleList", roles);
		mv.addObject("userId", userId);
		mv.addObject("roleIds", idBuilder.toString());		//绑定checkbox
		return mv;
	}

	/* --------------- action ---------------- */
	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@RequestMapping("/role/editRole")
	public ModelAndView editRole(VoShiroRole vo, int operType) {
		logger.info("name : " + vo.getRoleName());
		ModelAndView mv = null;
		if (vo.getRoleName().equals("") || vo.getRoleName().equals("")) {
			mv = new ModelAndView(return_edit);
			mv.addObject("errMsg", PremissionConstant.invalidParam);
			return mv;
		}
		boolean flag = true;
		String errMsg = PremissionConstant.noErr;
		try {
			if (operType == 1) {
				roleService.addRole(vo, ShiroBaseController.getCurrentUser());
			} else if (operType == 2) {
				roleService.updateRole(vo, ShiroBaseController.getCurrentUser());
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
	 * 删除角色
	 * 
	 * @return
	 */
	@RequestMapping("/role/del")
	public ModelAndView delRole(long id) {
		roleService.delRole(id);
		ModelAndView mv = new ModelAndView(redirect_list);
		return mv;
	}

}
