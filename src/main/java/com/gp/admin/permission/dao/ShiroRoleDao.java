package com.gp.admin.permission.dao;

import java.util.List;
import java.util.Map;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.permission.domain.ShiroRole;

/**
 * 
 * @author wangjiehan
 *
 */
public interface ShiroRoleDao extends BaseDao<ShiroRole> {

	public List<ShiroRole> getRolesByRoleIds(Map<String, Object> roleIds);
	
}
