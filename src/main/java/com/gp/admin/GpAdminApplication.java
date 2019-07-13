
package com.gp.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * 
 * @author wangjiehan
 *
 */
@SpringBootApplication(exclude = { JmxAutoConfiguration.class, WebSocketServletAutoConfiguration.class })
// @MapperScan("com.gp.admin.permission.dao")
@ImportResource("classpath:/shiro-beans-config.xml")
public class GpAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpAdminApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> getShiroFilter() {
		FilterRegistrationBean<DelegatingFilterProxy> frb = new FilterRegistrationBean<>(
				new DelegatingFilterProxy("shiroFilter"));
		frb.addUrlPatterns("/*");
		frb.addInitParameter("targetFilterLifecycle", "true");
		return frb;
	}

}
