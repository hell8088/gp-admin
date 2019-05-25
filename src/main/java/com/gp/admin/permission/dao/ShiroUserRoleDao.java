package com.gp.admin.permission.dao;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.permission.domain.ShiroUserRole;

/**
 * 
 * @author wangjiehan
 *
 */
public interface ShiroUserRoleDao extends BaseDao<ShiroUserRole>{

	int updateDeletedByUserId(long userId);
	
}
