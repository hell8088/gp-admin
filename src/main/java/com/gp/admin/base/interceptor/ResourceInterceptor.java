package com.gp.admin.base.interceptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gp.admin.biz.security.domain.SysResource;
import com.gp.admin.biz.security.domain.SysRole;
import com.gp.admin.biz.security.domain.SysUser;
import com.gp.admin.biz.security.service.SysResourceService;
import com.gp.admin.biz.security.service.SysRoleService;

/**
 *
 * @author: wangjiehan
 * @date: 2019年8月8日 下午3:52:08
 */
public class ResourceInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ResourceInterceptor.class);

	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysResourceService sysResourceService;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {

		boolean isAccess = false;
		String uri = httpServletRequest.getRequestURI().toString();
		// 获取用户
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		if (user == null) {
			return true;
		}
		// 获取用户角色
		List<SysRole> roles = sysRoleService.getRoles(user.getRoleIds());
		// 获取用户权限
		List<SysResource> sysResources = sysResourceService.getResources(roles);
		String resCategray = getResource(uri);
		for (SysResource resource : sysResources) {
			if (resource.getUrl() == null)
				continue;
			if (resource.getUrl().equals(""))
				continue;
			if (resCategray.equals(getResource(resource.getUrl()))) {
				isAccess = true;
				break;
			}
		}
		if (isAccess)
			return isAccess;
		else {
			httpServletResponse.sendRedirect("/401error");
			return false;
		}
	}

	private String getResource(String uri) {
		String rgex = "/(.*?)/";
		Pattern pattern = Pattern.compile(rgex);
		Matcher m = pattern.matcher(uri);
		if (m.find(0)) {
			return m.group(1);
		} else {
			return "";
		}
	}

}
