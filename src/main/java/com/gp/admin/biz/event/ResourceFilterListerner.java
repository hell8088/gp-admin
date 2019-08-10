package com.gp.admin.biz.event;

import javax.annotation.Resource;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ResourceFilterListerner implements ApplicationListener<ResourceEvent> {

	private final static Logger logger = LoggerFactory.getLogger(ResourceFilterListerner.class);
	
	@Resource
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	
	@Override
	public void onApplicationEvent(ResourceEvent event) {
		logger.info("zzz ResourceFilterListerner : " + event.getResourceKey());
		shiroFilterFactoryBean.getFilterChainDefinitionMap().put("resourceKey", "resourceUrl");
		logger.info("zzz : " + shiroFilterFactoryBean.getFilterChainDefinitionMap().get("resourceKey"));
		shiroFilterFactoryBean.getFilterChainDefinitionMap().remove("resourceKey");
		logger.info("zzz : " + shiroFilterFactoryBean.getFilterChainDefinitionMap().get("resourceKey"));
		
	}

}
