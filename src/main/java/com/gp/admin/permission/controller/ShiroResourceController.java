package com.gp.admin.permission.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gp.admin.permission.domain.ShiroResource;
import com.gp.admin.permission.domain.VoTreeNode;
import com.gp.admin.permission.service.ShiroResourceService;

/**
 * 
 * @author wangjiehan
 *
 */
@Controller
public class ShiroResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ShiroResourceController.class);
	private static final String return_list = "resource/list";
	// private static final String return_edit = "resource/edit";
	// private static final String redirect_list = "redirect:list";
	// private static final String redirect_edit = "redirect:edit";

	@Resource
	private ShiroResourceService resourceService;

	/* --------------- page redirect ---------------- */
	@RequestMapping("/resource/list")
	public ModelAndView list(String menuId) {
		// List<ShiroRole> roles = roleService.getRoles();
		ModelAndView mv = new ModelAndView(return_list);
		mv.addObject("menuId", menuId);
		// mv.addObject("roleList", roles);
		return mv;
	}

	/* --------------- action ---------------- */

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resource/addNode")
	public String addNode(String name, long pid) {
		logger.info("pid : " + pid);
		String currentUser = ShiroBaseController.getCurrentUser();
		ShiroResource node = resourceService.addNode(name, pid, currentUser);
		return node.getId().toString();
	}

	@ResponseBody
	@RequestMapping("/resource/loadTree")
	public List<VoTreeNode> loadTrees(long pid) throws JsonProcessingException {
		logger.info("pid : " + pid);
		List<ShiroResource> nodes = resourceService.getNodes(pid);
		List<VoTreeNode> voNodes = new ArrayList<>();
		for (ShiroResource res : nodes) {
			VoTreeNode voTreeNode = new VoTreeNode();
			voTreeNode.setId(res.getId());
			voTreeNode.setpId(res.getPid());
			voTreeNode.setName(res.getName());
			voTreeNode.setClick("editNode(this.id," + res.getId() + ")");
			String address = res.getResAddress();
			if (address == null || address.equals(""))
				address = "#";
			voTreeNode.setAddress(address);
			voNodes.add(voTreeNode);
		}
		// ObjectMapper objectMapper = new ObjectMapper();
		// String jsonStr = objectMapper.writeValueAsString(voNodes);
		return voNodes;
	}

	@ResponseBody
	@RequestMapping("/resource/getNode")
	public ShiroResource getNode(long resId) throws JsonProcessingException {
		logger.info("resId : " + resId);
		return resourceService.getNode(resId);
	}

	@ResponseBody
	@RequestMapping("/resource/updateNode")
	public String updateNode(ShiroResource model) throws JsonProcessingException {
		logger.info("model id : " + model.getId());
		ShiroResource oldModel = resourceService.getNode(model.getId());

		oldModel.setName(model.getName());
		oldModel.setResDesc(model.getResDesc());
		oldModel.setResCode(model.getResCode());
		oldModel.setResType(model.getResType());
		oldModel.setResAddress(model.getResAddress());
		oldModel.setModifier(ShiroBaseController.getCurrentUser());
		oldModel.setModifiedDate(new Date());

		// 更新资源信息
		resourceService.updateNode(oldModel);

		return "success";
	}

	@ResponseBody
	@RequestMapping("/resource/removeNode")
	public String removeNode(long id) {
		resourceService.delResource(id);
		return "success";
	}

}
