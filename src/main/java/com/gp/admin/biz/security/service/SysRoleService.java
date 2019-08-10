package com.gp.admin.biz.security.service;

import java.util.HashMap;
import java.util.LinkedList;
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

	public SysRole getRoleById(long id) {
		SysRole role = sysRoleDao.findByPrimaryKey(id);
		return role;
	}

	public void addRole(SysRole role) {
		Map<String, SysRole> param = new HashMap<>();
		param.put("model", role);
		sysRoleDao.insert(param);
	}

	public void updateRole(SysRole role) {
		Map<String, SysRole> param = new HashMap<>();
		param.put("model", role);
		sysRoleDao.update(param);
	}

	public int delRole(Long id) {
		return sysRoleDao.deleteByPrimaryKey(id);
	}

	/**
	 * 根据 id 集合查询角色
	 * @param roleIds
	 * @return
	 */
	public List<SysRole> getRoleByIds(List<Long> roleIds) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("roleIds", roleIds);
		return sysRoleDao.getRoleByIds(filter);
	}
	
	
	
	public List<SysRole> getRoles(String roleIds) {
		List<SysRole> list = null;
		String[] roleIdArray = roleIds.split(",");
		if (roleIdArray.length > 0) {
			List<Long> ids = new LinkedList<Long>();
			for (int i = 0; i < roleIdArray.length; i++) {
				ids.add(Long.valueOf(roleIdArray[i]));
			}
			list = getRoleByIds(ids);
		}
		return list;
	}

}
