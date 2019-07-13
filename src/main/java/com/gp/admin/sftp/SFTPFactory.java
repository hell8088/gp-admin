package com.gp.admin.sftp;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月4日 下午4:39:26
 */
@Component
public class SFTPFactory {

	@Resource
	private SFTPConfig sftpConfig;
	
	@Resource
	private GeneralSFTPClient sftpClient;
	
	protected SFTPFactory() {
		super();
	}

	public SFTPClient createDefault() {
		return sftpClient;
	}

	public SFTPClient createCustomer(SFTPConfig param) {
		return new GeneralSFTPClient(param);
	}

}
