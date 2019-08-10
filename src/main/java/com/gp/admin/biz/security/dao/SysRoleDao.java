package com.gp.admin.biz.security.dao;

import java.util.List;
import java.util.Map;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.biz.security.domain.SysRole;

/**  
 *
 * @author: wangjiehan
 * @date:   2019年7月16日 上午10:59:17   
 */
public interface SysRoleDao extends BaseDao<SysRole>{

	List<SysRole> findByPage(Map<String, Object> filter);
	
	List<SysRole> getRoleByIds(Map<String, Object> filter);
	
}
