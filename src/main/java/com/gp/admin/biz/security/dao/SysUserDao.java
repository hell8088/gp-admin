package com.gp.admin.biz.security.dao;

import java.util.List;
import java.util.Map;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.biz.security.domain.SysUser;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午10:58:05
 */
public interface SysUserDao extends BaseDao<SysUser> {

	List<SysUser> findByPage(Map<String, Object> filter);

	int lockUser(Map<String, Object> filter);
}
