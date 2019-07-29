package com.gp.admin.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gp.admin.base.filter.FirstFilter;
import com.gp.admin.base.filter.SecondFilter;
import com.gp.admin.base.interceptor.FirstInterceptor;

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
	public FilterRegistrationBean<FirstFilter> getFirstFilter() {
		FilterRegistrationBean<FirstFilter> firstBean = new FilterRegistrationBean<>();
		firstBean.setFilter(new FirstFilter());
		firstBean.addUrlPatterns("/*");
		logger.info("zzz firstFilter load success");
		return firstBean;
	}

	@Bean
	public FilterRegistrationBean<SecondFilter> getSecondFilter() {
		FilterRegistrationBean<SecondFilter> secondBean = new FilterRegistrationBean<>();
		secondBean.setFilter(new SecondFilter());
		secondBean.addUrlPatterns("/*");
		logger.info("zzz secondFilter load success");
		return secondBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new FirstInterceptor()).addPathPatterns("/**");
	}

}
