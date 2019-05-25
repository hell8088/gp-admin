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
import com.gp.admin.permission.dao.ShiroUserDao;
import com.gp.admin.permission.dao.ShiroUserRoleDao;
import com.gp.admin.permission.domain.ShiroUser;
import com.gp.admin.permission.domain.ShiroUserRole;
import com.gp.admin.permission.domain.VoShiroUser;

/**
 * 用户信息表
 * 
 * @author wangjiehan
 *
 */
@Service
public class ShiroUserService {

	private static final Logger logger = LoggerFactory.getLogger(ShiroUserService.class);

	@Resource
	private ShiroUserDao dao;

	@Resource
	private ShiroUserRoleDao userRoleDao;

	@Resource
	private DBService<ShiroUserDao, ShiroUser> dbService;

	@Resource
	private DBService<ShiroUserRoleDao, ShiroUserRole> userRoleService;

	public ShiroUserDao getDao() {
		return dao;
	}

	public DBService<ShiroUserDao, ShiroUser> getDbService() {
		return dbService;
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	public ShiroUser addUser(VoShiroUser vo, String currentUser) {
		ShiroUser user = new ShiroUser();
		user.setCreater(currentUser);
		user.setModifier(currentUser);
		user.setCreatedDate(new Date());
		user.setModifiedDate(new Date());
		user.setUserName(vo.getUserName());
		user.setPassword(vo.getPassword());
		user.setDeleted(DaoConstant.ACTIVE);

		int rowCount = getDbService().insert(getDao(), user);
		logger.info("zzz addUser rowCount : " + rowCount);
		return user;
	}

	/**
	 * 修改用户
	 * 
	 * @param vo
	 * @param currentUser
	 * @return
	 */
	public boolean updateUser(VoShiroUser vo, String currentUser) {
		ShiroUser user = getUserById(vo.getId());
		if (Objects.isNull(user)) {
			logger.info("user is not exists");
			return false;
		}
		if (vo.getPassword() != null && !vo.getPassword().equals("")) {
			user.setPassword(vo.getPassword());
			user.setModifiedDate(new Date());
			user.setModifier(currentUser);
			getDbService().update(dao, user);
		}
		return true;
	}

	/**
	 * 根据 ID 获取用户
	 * 
	 * @param id
	 * @return
	 */
	public ShiroUser getUserById(long id) {
		ShiroUser user = getDbService().findByPrimaryKey(dao, id);
		return user;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public boolean delUser(long id) {
		int rowCount = getDbService().deleteByPrimaryKey(getDao(), id);
		logger.info("zzz delUser rowCount : " + rowCount);
		// 删除关系表
		delUserRole(id);
		return true;
	}

	/**
	 * 获取用户列表
	 * 
	 * @return
	 */
	public List<ShiroUser> getUsers() {
		Map<String, Object> filters = new HashMap<>();
		List<ShiroUser> users = getDbService().find(getDao(), filters);
		return users;
	}

	/**
	 * 编辑用户所属角色
	 * 
	 * @return
	 */
	public boolean editUserRole(VoShiroUser vo, String currentUser) {
		ShiroUser user = getDbService().findByPrimaryKey(getDao(), vo.getId());
		if (Objects.isNull(user)) {
			return false;
		}

		user.setUserName(vo.getUserName());
		user.setPassword(vo.getPassword());
		user.setModifiedDate(new Date());
		user.setModifier(currentUser);

		getDbService().update(getDao(), user);
		return true;
	}

	/**
	 * 分配用户权限
	 * 
	 * @param userId
	 * @param roleIds
	 * @param currentUser
	 * @return
	 */
	public boolean addUserRole(long userId, List<Long> roleIds, String currentUser) {
		// 刪除用户现有权限
		// getUserRoleService().delete(dao, t)
		userRoleDao.updateDeletedByUserId(userId);

		// 添加用户新权限
		List<ShiroUserRole> models = new ArrayList<ShiroUserRole>();
		for (long roleId : roleIds) {
			ShiroUserRole model = new ShiroUserRole();
			model.setUserId(userId);
			model.setRoleId(roleId);
			model.setCreatedDate(new Date());
			model.setModified_date(new Date());
			model.setCreater(currentUser);
			model.setModifier(currentUser);
			model.setDeleted(DaoConstant.ACTIVE);
			models.add(model);
		}
		userRoleDao.batchInsert(models);
		return true;
	}

	/**
	 * 删除用户,连带删除角色关系
	 * 
	 * @return
	 */
	private boolean delUserRole(long id) {
		int rowCount = 0;// getDbService().deleteByPrimaryKey(getDao(), id);
		logger.info("zzz delUserRole rowCount : " + rowCount);
		return true;
	}

}
