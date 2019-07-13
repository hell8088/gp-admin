package com.gp.admin.permission.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.admin.permission.domain.ShiroUser;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月11日 下午2:45:19
 */
public class CustomerRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRealm.class);

	@Autowired
	private ShiroUserService shiroUserService;

	/**
	 * 鉴权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// 从 principals获取主身份信息
		// 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
//		ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
//		Long userId = user.getId();
//		List<Long> roleIds = roleService.getRoleIds(userId);
		
		List<String> resAddressList = new ArrayList<String>();
		resAddressList.add("url1");
		resAddressList.add("url2");
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(resAddressList);
		return simpleAuthorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("zzz 认证");
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = null;
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String userName = userToken.getUsername();
		
		if (userName != null && !"".equals(userName)) {
			logger.info("userName : " + userName);
			Map<String, Object> filter = new HashMap<>();
			filter.put("userName", userName);
			List<ShiroUser> users = shiroUserService.getDao().find(filter);
			if (users != null && users.size() > 0) {
				ShiroUser user = users.get(0);
				String password = new String(userToken.getPassword());
				if (password.equals(user.getPassword())) {
					simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
							getName());
				}
			} else {
				logger.info("zzz 认证失败 user is null");
			}
		} else {
			logger.info("zzz 认证失败 userName is null");
		}
		return simpleAuthenticationInfo;
	}

}
