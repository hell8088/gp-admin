package com.gp.admin;

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
//@MapperScan("com.gp.admin.permission.dao")
public class GpAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpAdminApplication.class, args);
	}

}
