package com.gp.admin.biz.security.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.gp.admin.biz.security.domain.SysResource;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月23日 下午2:33:03
 */
@Configuration
public class ShiroConfig {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
	
	@Resource
	private SysResourceService sysResourceService;
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		logger.info("init shiroFilter");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager,Shiro的核心安全接口
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 这里的/login是后台的接口名,非页面，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 这里的/index是后台的接口名,非页面,登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/user/list");
		// 未授权界面,该配置无效，并不会进行页面跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

		// 自定义拦截器限制并发人数,参考博客：
		// Map<String, Filter> filtersMap = new LinkedHashMap<>();
		// 限制同一帐号同时在线的个数
		// filtersMap.put("kickout", kickoutSessionControlFilter());
		// shiroFilterFactoryBean.setFilters(filtersMap);

		// 配置访问权限 必须是LinkedHashMap，因为它必须保证有序
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 一定要注意顺序,否则就不好使了
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 配置不登录可以访问的资源，anon 表示资源都可以匿名访问
		// 因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**","anon")来配置匿名访问，必须配置到每个静态目录
		filterChainDefinitionMap.put("/assets/**", "anon");
		// logout是shiro提供的过滤器
		filterChainDefinitionMap.put("/logout", "logout");

		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		// 此时访问/userInfo/del需要del权限,在自定义Realm中为用户授权。
		// filterChainDefinitionMap.put("/userInfo/del",
		// "perms[\"userInfo:del\"]");

		
		List<SysResource> resources = sysResourceService.getResources(0);
		for(SysResource res : resources){
			System.out.println("zzz : " + res.getUrl());
		}
		
		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(customerRealm());
		return securityManager;
	}

	@Bean
	public CustomerRealm customerRealm() {
		CustomerRealm myShiroRealm = new CustomerRealm();

		// 加密
		// myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());

		// 配置记住我
		// securityManager.setRememberMeManager(rememberMeManager());

		// 配置 redis缓存管理器
		// securityManager.setCacheManager(getEhCacheManager());

		// 配置自定义session管理 （可使用redis）
		// securityManager.setSessionManager(sessionManager());

		return myShiroRealm;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于
														// md5(md5(""));
		return hashedCredentialsMatcher;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");// 数据库异常处理
		mappings.setProperty("UnauthorizedException", "/user/403");
		r.setExceptionMappings(mappings); // None by default
		r.setDefaultErrorView("error"); // No default
		r.setExceptionAttribute("exception"); // Default is "exception"
		// r.setWarnLogCategory("example.MvcLogger"); // No default
		return r;
	}

	/**
	 * 必须（thymeleaf页面使用shiro标签控制按钮是否显示） 未引入thymeleaf包，Caused by:
	 * java.lang.ClassNotFoundException: org.thymeleaf.dialect.AbstractProcessorDialect
	 * @return
	 */
//	@Bean
//	public ShiroDialect shiroDialect() {
//		return new ShiroDialect();
//	}

}
