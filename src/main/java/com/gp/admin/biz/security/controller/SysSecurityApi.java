package com.gp.admin.biz.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gp.admin.base.domain.ResultData;
import com.gp.admin.base.service.ResultBean;
import com.gp.admin.biz.security.domain.SysResource;
import com.gp.admin.biz.security.domain.SysRole;
import com.gp.admin.biz.security.domain.SysUser;
import com.gp.admin.biz.security.service.SysResourceService;
import com.gp.admin.biz.security.service.SysRoleService;
import com.gp.admin.biz.security.service.SysUserService;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 下午4:52:32
 */

@RestController
@RequestMapping("/api")
public class SysSecurityApi {

	private static final Logger logger = LoggerFactory.getLogger(SysSecurityApi.class);

	@Resource
	private SysResourceService resourceService;

	@Resource
	private SysUserService userService;

	@Resource
	private SysRoleService roleService;

	/*--------------------------  resuource  --------------------------*/

	@RequestMapping("/resource/data")
	public Object getResource(long parentId) {
		List<SysResource> list = resourceService.getResources(parentId);
		return ResultBean.successBuild(list, list.size());
	}

	/**
	 * 获取左侧导航树
	 * 
	 * @param pid
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/resource/leftnav")
	public List<VoTreeNode> loadTrees(long pid) throws JsonProcessingException {
		logger.info("pid : " + pid);
		List<SysResource> nodes = resourceService.getResources(pid);
		List<VoTreeNode> voNodes = new ArrayList<>();
		for (SysResource res : nodes) {
			VoTreeNode voTreeNode = buildNavTreeNode(res);
			voNodes.add(voTreeNode);
		}
		return voNodes;
	}

	/**
	 * 根据角色加载资源树
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/resource/tree")
	private List<VoResourceTree> resourceTreeForRole(Long roleId) {
		SysResource root = null;
		List<VoResourceTree> treeNodes = new LinkedList<>();
		List<SysResource> list = resourceService.getResources(0); // 获取所有资源节点
		if (list.size() > 0) {
			// 构建子节点 map
			Map<Long, List<SysResource>> children = buildChildren(list);
			root = children.get(0L).get(0); // 获取根结点
			
			
			Map<Long, Boolean> roleIdMap = null;
			// 绑定已有权限节点
			SysRole role = roleService.getRoleById(roleId == null ? 0 : roleId);
			if (role != null) {
				String ids = role.getResourceIds();
				if (ids.length() > 0) {
					roleIdMap = new HashMap<>();
					String[] strArray = ids.split(",");
					for (String resourceId : strArray) {
						roleIdMap.put(Long.valueOf(resourceId), true);
					}
				}
			}
			// 从 root 开始构建资源树
			treeNodes.add(buildRsTreeNode(root, children, roleIdMap));
		}
		return treeNodes;
	}

	/*--------------------------  role  --------------------------*/

	@RequestMapping("/role/save")
	private String saveRole(Long roleId, String roleName, String description, String treeNodes) {
		String ids = "";
		if (!treeNodes.equals("[]")) {
			List<VoResourceTree> list = JSON.parseArray(treeNodes, VoResourceTree.class);
			ids = buildResourceIds(list.get(0));
		}
		if (roleId == null || roleId == 0) { // add
			roleService.addRole(resourceService.buildRole(roleId, roleName, description, ids));
		} else { // update
			roleService.updateRole(resourceService.buildRole(roleId, roleName, description, ids));
		}
		return "success";
	}

	@RequestMapping("/role/data")
	public ResultData<List<SysRole>> getRole(int page, int limit) {
		List<SysRole> list = roleService.getRole(page, limit);
		long count = roleService.getRoleCount();
		return ResultBean.successBuild(list, count);
	}

	@RequestMapping("/role/all")
	public List<SysRole> getRole() {
		List<SysRole> list = roleService.getAllRole();
		return list;
	}

	/*--------------------------  user  --------------------------*/

	@RequestMapping("/user/save")
	private String saveUser(Long id, String username, String password, String roleIds) {
		if (id == null || id == 0) // add
			userService.addUser(username, password, roleIds);
		else // update
			userService.updateUser(id, username, password, roleIds);
		return "success";
	}

	@RequestMapping("/user/data")
	public ResultData<List<SysUser>> getUser(int page, int limit) {
		List<SysUser> list = userService.getUser(page, limit);
		long count = userService.getUserCount();
		return ResultBean.successBuild(list, count);
	}

	/*--------------------------  private  --------------------------*/

	/**
	 * 构建左侧菜单节点
	 * 
	 * @param res
	 * @return
	 */
	private VoTreeNode buildNavTreeNode(SysResource res) {
		VoTreeNode voTreeNode = new VoTreeNode();
		voTreeNode.setId(res.getId());
		voTreeNode.setpId(res.getParentId());
		voTreeNode.setName(res.getName());
		String url = res.getUrl();
		if (url == null || url.equals(""))
			url = "#";
		voTreeNode.setUrl(url);
		return voTreeNode;
	}

	/**
	 * 递归构造资源树节点
	 * 
	 * @param resource
	 * @param childrenMap
	 * @return
	 */
	private VoResourceTree buildRsTreeNode(SysResource resource, Map<Long, List<SysResource>> childrenMap,
			Map<Long, Boolean> roleIdMap) {

		VoResourceTree tree = new VoResourceTree();
		tree.setId(resource.getId());
		tree.setTitle(resource.getName());
		List<VoResourceTree> childNodes = new LinkedList<>();

		// 当前节点存在子节点
		if (childrenMap.containsKey(resource.getId())) {
			List<SysResource> childList = childrenMap.get(resource.getId());
			for (SysResource childResource : childList) {
				VoResourceTree rsTree = buildRsTreeNode(childResource, childrenMap, roleIdMap); // 递归构建
				childNodes.add(rsTree);
			}
		}

		// 角色拥有资源节点设置为选中
		if (roleIdMap != null)
			if (roleIdMap.get(tree.getId()) != null)
				tree.setChecked(roleIdMap.get(tree.getId()));
		tree.setChildren(childNodes);

		// root 节点默认展开
		if (resource.getParentId() == 0) {
			tree.setSpread(true);
		}
		return tree;
	}

	/**
	 * 根据前端传入的资源树，解析角色拥有的资源id
	 * 
	 * @return
	 */
	private String buildResourceIds(VoResourceTree node) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(node.getId()).append(",");
		if (node.getChildren().size() > 0) {
			for (VoResourceTree child : node.getChildren()) {
				String ids = buildResourceIds(child);
				strBuilder.append(ids);
			}
		}
		return strBuilder.toString();
	}

	private Map<Long, List<SysResource>> buildChildren(List<SysResource> list) {
		Map<Long, List<SysResource>> children = new HashMap<>();
		for (SysResource resource : list) {
			if (children.containsKey(resource.getParentId())) {
				List<SysResource> childList = children.get(resource.getParentId());
				childList.add(resource);
			} else {
				List<SysResource> childList = new LinkedList<>();
				childList.add(resource);
				children.put(resource.getParentId(), childList);
			}
		}
		return children;
	}

}
