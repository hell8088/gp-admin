package com.gp.admin.sftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月4日 下午8:20:08
 */

@PropertySource(value = { "classpath:properties/sftp.properties" })
@Component("sftpConfig")
@ConfigurationProperties(prefix = "sftp")
public class SFTPConfig {

	public final static String STRICT_HOSTKEY_CHECKING = "no";
	public final static String PREFERRED_AUTHENTICATIONS = "publickey,keyboard-interactive,password";

	public SFTPConfig() {

	}

	public SFTPConfig(String host, int port, int timeOut, String userName, String password, String rasPath) {
		this.host = host;
		this.port = port;
		this.timeOut = timeOut;
		this.userName = userName;
		this.password = password;
		this.rasPath = rasPath;
		chkParam();
	}

	@Value("${sftp.username}")
	private String userName;

	@Value("${sftp.password}")
	private String password;

	@Value("${sftp.host}")
	private String host;

	@Value("${sftp.port}")
	private int port;

	@Value("${sftp.timeout}")
	private int timeOut;

	@Value("${sftp.rsapath}")
	private String rasPath;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public String getRasPath() {
		return rasPath;
	}

	private void chkParam() {
		if (StringUtils.isEmpty(this.userName)) {

		}
		if (StringUtils.isEmpty(this.host)) {

		}
		if (port == 0) {

		}
	}

}
