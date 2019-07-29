package com.gp.admin.biz.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.admin.biz.security.dao.SysRoleDao;
import com.gp.admin.biz.security.domain.SysRole;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月22日 上午11:10:15
 */
@Service
public class SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	public List<SysRole> getAllRole() {
		List<SysRole> list = sysRoleDao.find(new HashMap<>());
		return list;
	}
	
	public List<SysRole> getRole(int page, int limit) {
		Map<String, Object> filter = new HashMap<>();
		int offset = (page - 1) * limit;
		filter.put("limit", limit);
		filter.put("offset", offset);
		List<SysRole> list = sysRoleDao.findByPage(filter);
		return list;
	}

	public long getRoleCount() {
		return sysRoleDao.findCount(new HashMap<>());
	}
	
	public SysRole getRoleById(long id){
		SysRole role = sysRoleDao.findByPrimaryKey(id);
		return role;
	}

	public void addRole(SysRole role) {
		Map<String, SysRole> param = new HashMap<>();
		param.put("model", role);
		sysRoleDao.insert(param);
	}
	
	public void updateRole(SysRole role){
		Map<String, SysRole> param = new HashMap<>();
		param.put("model", role);
		sysRoleDao.update(param);
	}

}
