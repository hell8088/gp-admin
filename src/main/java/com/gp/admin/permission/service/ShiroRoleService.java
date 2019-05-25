package com.gp.admin.permission.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gp.admin.base.dao.DaoConstant;
import com.gp.admin.base.service.DBService;
import com.gp.admin.permission.dao.ShiroRoleDao;
import com.gp.admin.permission.dao.ShiroUserRoleDao;
import com.gp.admin.permission.domain.ShiroRole;
import com.gp.admin.permission.domain.ShiroUserRole;
import com.gp.admin.permission.domain.VoShiroRole;

/**
 * 角色信息表
 * 
 * @author wangjiehan
 *
 */
@Service
public class ShiroRoleService {

	private static final Logger logger = LoggerFactory.getLogger(ShiroRoleService.class);

	@Resource
	private ShiroRoleDao roleDao;

	@Resource
	private DBService<ShiroRoleDao, ShiroRole> dbService;

	@Resource
	private ShiroUserRoleDao shiroUserRoleDao;

	public ShiroRoleDao getDao() {
		return roleDao;
	}

	public DBService<ShiroRoleDao, ShiroRole> getDbService() {
		return dbService;
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	public ShiroRole addRole(VoShiroRole vo, String currentUser) {
		ShiroRole user = new ShiroRole();
		user.setCreater(currentUser);
		user.setModifier(currentUser);
		user.setCreatedDate(new Date());
		user.setModifiedDate(new Date());
		user.setRoleName(vo.getRoleName());
		user.setRoleDesc(vo.getRoleDesc());
		user.setDeleted(DaoConstant.ACTIVE);

		int rowCount = getDbService().insert(getDao(), user);
		logger.info("zzz addRole rowCount : " + rowCount);
		return user;
	}

	/**
	 * 修改角色
	 * 
	 * @param vo
	 * @param currentUser
	 * @return
	 */
	public boolean updateRole(VoShiroRole vo, String currentUser) {
		ShiroRole role = getRoleById(vo.getId());
		if (Objects.isNull(role)) {
			logger.info("role is not exists");
			return false;
		}
		if (!vo.getRoleName().equals("")) {

			role.setRoleName(vo.getRoleName());
			role.setRoleDesc(vo.getRoleDesc());
			role.setModifier(currentUser);
			role.setModifiedDate(new Date());
			getDbService().update(getDao(), role);
		}
		return true;
	}

	/**
	 * 根据 ID 返回角色
	 * 
	 * @param id
	 * @return
	 */
	public ShiroRole getRoleById(long id) {
		ShiroRole role = getDbService().findByPrimaryKey(getDao(), id);
		return role;
	}

	/**
	 * 返回角色列表
	 * 
	 * @return
	 */
	public List<ShiroRole> getRoles() {
		Map<String, Object> filters = new HashMap<>();
		List<ShiroRole> roles = getDbService().find(getDao(), filters);
		return roles;
	}

	/**
	 * 编辑角色所有资源
	 * 
	 * @return
	 */
	public boolean editRoleResource() {
		return false;
	}

	/**
	 * 删除角色
	 * 
	 * @return
	 */
	public boolean delRole(long id) {
		int rowCount = getDbService().deleteByPrimaryKey(getDao(), id);
		logger.info("zzz delRole rowCount : " + rowCount);
		// 删除关系表
		// delUserRole(id);
		// delRoleResource(id);
		return true;
	}

	/**
	 * 删除角色,连带删除资源关系
	 * 
	 * @return
	 */
	private boolean delRoleResource() {
		return false;
	}

	/**
	 * 删除用户,连带删除角色
	 * 
	 * @return
	 */
	private boolean delUserRole() {
		return false;
	}

	/**
	 * 根据 用户 ID 获取角色集合
	 * 
	 * @param userId
	 * @return
	 */
	public List<ShiroRole> getRoles(Long userId) {
		List<ShiroRole> roles = null;
		List<Long> roleIds = getRoleIds(userId);
		if (roleIds.size() > 0) {
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("roleIds", roleIds);
			roles = roleDao.getRolesByRoleIds(filters);
		}
		return roles;
	}

	/**
	 * 根据 用户 ID 获取 角色 ID 集合
	 * 
	 * @param userId
	 * @return
	 */
	public List<Long> getRoleIds(Long userId) {
		// 获取角色关系 Id 集合
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("userId", userId);
		List<ShiroUserRole> shiroUserRoles = shiroUserRoleDao.find(filters);

		// 获取角色 id 集合
		List<Long> roleIds = new ArrayList<Long>();
		for (ShiroUserRole userRole : shiroUserRoles) {
			roleIds.add(userRole.getRoleId());
		}
		return roleIds;
	}

}
