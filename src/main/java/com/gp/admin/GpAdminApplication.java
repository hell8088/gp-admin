package com.gp.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;

/**
 * 
 * @author wangjiehan
 *
 */
@SpringBootApplication(exclude = { JmxAutoConfiguration.class, WebSocketServletAutoConfiguration.class })
public class GpAdminApplication {

	private static final Logger logger = LoggerFactory.getLogger(GpAdminApplication.class);

	public static void main(String[] args) {
		logger.info("GP Start");
		SpringApplication.run(GpAdminApplication.class, args);
	}

}
