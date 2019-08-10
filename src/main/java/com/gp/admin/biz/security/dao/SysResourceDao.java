package com.gp.admin.biz.security.dao;

import java.util.List;
import java.util.Map;

import com.gp.admin.base.dao.BaseDao;
import com.gp.admin.biz.security.domain.SysResource;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午10:59:29
 */
public interface SysResourceDao extends BaseDao<SysResource> {

	List<SysResource> getResourceByIds(Map<String, Object> filter);

}
