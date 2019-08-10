package com.gp.admin.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gp.admin.base.filter.ResourceFilter;
import com.gp.admin.base.interceptor.ResourceInterceptor;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午8:59:26
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> getShiroFilter() {
		FilterRegistrationBean<DelegatingFilterProxy> frb = new FilterRegistrationBean<>(
				new DelegatingFilterProxy("shiroFilter"));
		frb.addUrlPatterns("/*");
		frb.addInitParameter("targetFilterLifecycle", "true");
		logger.info("zzz shiroFilter load success");
		return frb;
	}

	@Bean
	public FilterRegistrationBean<ResourceFilter> getResourceFilter() {
		FilterRegistrationBean<ResourceFilter> resourceFilter = new FilterRegistrationBean<>();
		resourceFilter.setFilter(new ResourceFilter());
		resourceFilter.addUrlPatterns("/*");
		logger.info("zzz ResourceFilter load success");
		return resourceFilter;
	}

	@Bean
	public ResourceInterceptor getResourceInterceptor() {
		return new ResourceInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getResourceInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/login",
				"/logout", "/assets/**", "/api/**");
	}

}
