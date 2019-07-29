package com.gp.admin.biz.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gp.admin.base.dao.DataStatus;
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

	private String getLoginUser() {
		return "admin";
	}

}
