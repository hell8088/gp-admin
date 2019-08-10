package com.gp.admin.biz.security.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gp.admin.biz.security.domain.SysResource;
import com.gp.admin.biz.security.domain.SysRole;
import com.gp.admin.biz.security.domain.SysUser;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月11日 下午2:45:19
 */
public class CustomerRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRealm.class);

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysResourceService sysResourceService;

	/**
	 * 授权用户权限
	 * 授权的方法是在碰到<shiro:hasPermission name=''></shiro:hasPermission>标签的时候调用的
	 * 它会去检测shiro框架中的权限(这里的permissions)是否包含有该标签的name值,如果有,里面的内容显示
	 * 如果没有,里面的内容不予显示(这就完成了对于权限的认证.)
	 *
	 * shiro 的权限授权是通过继承 AuthorizingRealm 抽象类，重载 doGetAuthorizationInfo();
	 * 当访问到页面的时候，链接配置了相应的权限或者 shiro 标签才会执行此方法否则不会执行
	 * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回 null 即可。
	 *
	 * 在这个方法中主要是使用类：SimpleAuthorizationInfo 进行角色的添加和权限的添加。
	 * authorizationInfo.addRole(role.getRole());
	 * authorizationInfo.addStringPermission(p.getPermission());
	 *
	 * 当然也可以添加set集合：roles是从数据库查询的当前用户的角色，stringPermissions是从数据库查询的当前用户对应的权限
	 * authorizationInfo.setRoles(roles);
	 * authorizationInfo.setStringPermissions(stringPermissions);
	 *
	 * 就是说如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add", "perms[权限添加]");
	 * 就说明访问/add这个链接必须要有“权限添加”这个权限才可以访问
	 *
	 * 如果在shiro配置文件中添加了filterChainDefinitionMap.put("/add",
	 * "roles[100002]，perms[权限添加]"); 就说明访问/add这个链接必须要有 "权限添加" 这个权限和具有 "100002"
	 * 这个角色才可以访问
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		logger.info("鉴权");
		SimpleAuthorizationInfo authorizationInfo = null;
		// 获取用户
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		// 获取用户角色
		List<SysRole> roles = sysRoleService.getRoles(user.getRoleIds());
		if (roles != null && roles.size() > 0) {
			authorizationInfo = new SimpleAuthorizationInfo();
			// 添加角色
			for (SysRole role : roles) {
				authorizationInfo.addRole(role.getRole());
			}
		}
		// 获取用户权限
		List<SysResource> sysResources = sysResourceService.getResources(roles);
		// 添加权限
		for (SysResource sysResource : sysResources) {
			authorizationInfo.addStringPermission(sysResource.getUrl());
		}
		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("认证");
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		SysUser user = getUser(userToken.getUsername());
		if (user == null) {
			throw new UnknownAccountException("用户不存在！");
		}
		String password = new String(userToken.getPassword());
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("密码错误！");
		}
		if ("0".equals(user.getLocked())) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				getName());
		return simpleAuthenticationInfo;
	}

	private SysUser getUser(String userName) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("username", userName);
		List<SysUser> users = sysUserService.getUsers(filter);
		if (users.size() > 0)
			return users.get(0);
		else
			return null;
	}



}
