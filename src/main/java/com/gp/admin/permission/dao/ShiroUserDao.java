package com.gp.admin.permission.dao;

import java.util.List;
import java.util.Map;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.permission.domain.ShiroUser;

/**
 * 
 * @author wangjiehan
 *
 */
public interface ShiroUserDao extends BaseDao<ShiroUser>{

	List<ShiroUser> findByPage(Map<String, Object> pageFilter);
	
}
