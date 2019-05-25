package com.gp.admin.permission.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gp.admin.base.dao.DaoConstant;
import com.gp.admin.base.service.DBService;
import com.gp.admin.permission.dao.ShiroResourceDao;
import com.gp.admin.permission.domain.ShiroResource;

/**
 * 
 * @author wangjiehan
 *
 */
@Service
public class ShiroResourceService {

	@Resource
	private ShiroResourceDao resourceDao;

	@Resource
	private DBService<ShiroResourceDao, ShiroResource> dbService;

	public ShiroResourceDao getResourceDao() {
		return resourceDao;
	}

	public DBService<ShiroResourceDao, ShiroResource> getDbService() {
		return dbService;
	}

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	public ShiroResource addNode(String name, long pid, String currentUser) {
		ShiroResource node = new ShiroResource();
		node.setDeleted(DaoConstant.ACTIVE);
		node.setCreater(currentUser);
		node.setCreated_date(new Date());
		node.setModifier(currentUser);
		node.setModified_date(new Date());
		node.setPid(pid);
		node.setName(name);

		getDbService().insert(resourceDao, node);
		long id = resourceDao.lastSequence();
		node.setId(id);
		return node;
	}

	/**
	 * 根据父 id 获取节点集合
	 * 
	 * @param pid
	 * @return
	 */
	public List<ShiroResource> getNodes(long pid) {
		Map<String, Object> filters = new HashMap<>();
		if (pid != 0) {
			filters.put("pid", pid);
		}
		List<ShiroResource> resources = getDbService().find(resourceDao, filters);
		return resources;
	}

	/**
	 * 获取节点信息
	 * 
	 * @param id
	 * @return
	 */
	public ShiroResource getNode(long id) {
		return getDbService().findByPrimaryKey(resourceDao, id);
	}

	/**
	 * 修改节点信息
	 * 
	 * @param node
	 * @return
	 */
	public int updateNode(ShiroResource node) {
		int rowCount = getDbService().update(resourceDao, node);
		return rowCount;
	}

	/**
	 * 删除资源
	 * 
	 * @return
	 */
	public boolean delResource(long id) {
		int rowCount = getDbService().deleteByPrimaryKey(resourceDao, id);
		//delRoleResource();
		return true;
	}

	/**
	 * 编辑资源
	 * 
	 * @return
	 */
	public boolean editResource() {
		return false;
	}

	/**
	 * 获取资源列表
	 * 
	 * @return
	 */
	public List<ShiroResource> getResources() {
		return null;
	}

	/**
	 * 删除资源,连带删除角色资源关系
	 * 
	 * @return
	 */
	private boolean delRoleResource() {
		return false;
	}
}
