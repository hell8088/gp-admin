package com.gp.admin.biz.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gp.admin.base.dao.DataStatus;
import com.gp.admin.biz.security.dao.SysUserDao;
import com.gp.admin.biz.security.domain.SysUser;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月17日 下午4:45:59
 */
@Service
public class SysUserService {

	@Resource
	private SysUserDao sysUserDao;

	public List<SysUser> getUsers(Map<String, Object> filter) {
		List<SysUser> users = sysUserDao.find(filter);
		return users;
	}

	public List<SysUser> getUser(int page, int limit) {
		Map<String, Object> filter = new HashMap<>();
		int offset = (page - 1) * limit;
		filter.put("limit", limit);
		filter.put("offset", offset);
		List<SysUser> list = sysUserDao.findByPage(filter);
		return list;
	}

	public long getUserCount() {
		return sysUserDao.findCount(new HashMap<>());
	}

	public SysUser getUserById(long id) {
		return sysUserDao.findByPrimaryKey(id);
	}

	public int addUser(String username, String password, String roleIds) {
		SysUser user = new SysUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setRoleIds(roleIds);
		user.setDeleted(DataStatus.ACTIVE.getCode());
		user.setCreated_date(new Date());
		user.setCreater("addUser");
		user.setModified_date(new Date());
		user.setModifier("addUser");
		user.setLocked(true);

		Map<String, SysUser> param = new HashMap<>();
		param.put("model", user);
		return sysUserDao.insert(param);
	}

	public int updateUser(Long id, String username, String password, String roleIds) {
		SysUser user = sysUserDao.findByPrimaryKey(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setRoleIds(roleIds);
		user.setModifier("updateUser");
		user.setModifiedDate(new Date());

		Map<String, SysUser> param = new HashMap<>();
		param.put("model", user);
		return sysUserDao.update(param);
	}

	public int delUser(Long id) {
		return sysUserDao.deleteByPrimaryKey(id);
	}

	public int lockUser(Long id, Boolean lockStatus) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("id", id);
		filter.put("lockStatus", lockStatus);
		return sysUserDao.lockUser(filter);
	}
}
