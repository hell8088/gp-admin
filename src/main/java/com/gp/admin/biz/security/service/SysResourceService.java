package com.gp.admin.biz.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gp.admin.base.dao.DataStatus;
import com.gp.admin.base.domain.ResultData;
import com.gp.admin.base.service.ResultBean;
import com.gp.admin.biz.security.dao.SysResourceDao;
import com.gp.admin.biz.security.domain.SysResource;
import com.gp.admin.biz.security.domain.SysRole;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午11:32:21
 */

@Service
public class SysResourceService {

	@Resource
	private SysResourceDao sysResourceDao;

	public List<SysResource> getResources(long parentId) {
		Map<String, Object> filters = new HashMap<>();
		if (parentId != 0) {
			filters.put("parentId", parentId);
		}
		List<SysResource> resources = sysResourceDao.find(filters);
		return resources;
	}

	public SysRole buildRole(Long id, String roleName, String description, String ids) {
		SysRole role = new SysRole();
		role.setId(id);
		role.setRole(roleName);
		role.setDescription(description);
		role.setResourceIds(ids);
		role.setDeleted(DataStatus.ACTIVE.getCode());
		role.setCreater(getLoginUser());
		role.setCreatedDate(new Date());
		role.setModifier(getLoginUser());
		role.setModified_date(new Date());
		role.setAvailable(true);
		return role;
	}

	public void resourceAdd(SysResource model) {
		model.setCreater(getLoginUser());
		model.setModifier(getLoginUser());
		model.setCreatedDate(new Date());
		model.setModifiedDate(new Date());
		model.setDeleted(0);
		model.setAvailable(true);
		Map<String, SysResource> param = new HashMap<>();
		param.put("model", model);
		sysResourceDao.insert(param);
	}

	public SysResource getModel(Long id) {
		return sysResourceDao.findByPrimaryKey(id);
	}

	public void resourceUpdate(SysResource model) {
		SysResource old = getModel(model.getId());
		if (!Objects.isNull(model)) {
			old.setName(model.getName());
			old.setUrl(model.getUrl());
			old.setPermission(model.getPermission());
			old.setType(model.getType());
			old.setPriority(model.getPriority());
			old.setModifier(getLoginUser());
			old.setModifiedDate(model.getModifiedDate());

			Map<String, SysResource> param = new HashMap<>();
			param.put("model", old);
			sysResourceDao.update(param);
		}
	}

	public Map<String, String> getResourceType() {
		Map<String, String> typeMap = new HashMap<>();
		typeMap.put("menu", "目录");
		typeMap.put("btn", "按钮");
		return typeMap;
	}

	public ResultData<Integer> resourceDelete(long id) {
		Map<String, Object> filters = new HashMap<>();
		filters.put("parentId", id);
		long subCount = sysResourceDao.findCount(filters);
		if (subCount > 0) {
			return ResultBean.failBuild(0, 0, "存在子目录，不能删除！");
		} else {
			int rowCount = sysResourceDao.deleteByPrimaryKey(id);
			return ResultBean.successBuild(rowCount, 0);
		}
	}

	/**
	 * 根据 id 集合查询角色
	 * @param roleIds
	 * @return
	 */
	public List<SysResource> getResourceByIds(List<Long> resourceIds) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("resourceIds", resourceIds);
		return sysResourceDao.getResourceByIds(filter);
	}
	
	
	public List<SysResource> getResources(List<SysRole> roles) {
		Set<Long> resourceIds = new HashSet<>();
		for (SysRole role : roles) {
			String tmpId = role.getResourceIds();
			if (tmpId == null)
				continue;
			String[] tmpIds = tmpId.split(",");
			for (int i = 0; i < tmpIds.length; i++) {
				if (tmpIds[i] == null)
					continue;
				resourceIds.add(Long.valueOf(tmpIds[i]));
			}
		}
		List<SysResource> sysResources = null;
		if (resourceIds.size() > 0) {
			List<Long> ids = new LinkedList<>(resourceIds);
			sysResources = getResourceByIds(ids);
		}
		return sysResources;
	}

	
	
	private String getLoginUser() {
		return "admin";
	}

}
