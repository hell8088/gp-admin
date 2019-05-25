package com.gp.admin.permission.dao;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.permission.domain.ShiroResource;

/**
 * 
 * @author wangjiehan
 *
 */
public interface ShiroResourceDao extends BaseDao<ShiroResource>{

	public long lastSequence();
	
}
