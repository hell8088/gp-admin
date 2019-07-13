package com.gp.admin.sftp;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月4日 下午2:56:32
 */
@Component("sftpHelper")
public class SFTPHelper {

	@Resource
	private SFTPClient sftpClient;

	public SFTPHelper() {

	}

	public boolean upload(String localPath, String sftpPath) {
		try {
			sftpClient.login();
			sftpClient.upload(localPath, sftpPath);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			sftpClient.logout();
		}
	}

	public void download(String sftpPath, String localPath) {
		try {
			sftpClient.login();
			sftpClient.download(sftpPath, localPath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sftpClient.logout();
		}
	}

	public boolean exists(String path) {
		sftpClient.login();
		boolean flag = sftpClient.exists(path);
		sftpClient.logout();
		return flag;
	}

	public void rename(String filePath1, String filePath2) {
		sftpClient.login();
		sftpClient.rename(filePath1, filePath2);
		sftpClient.logout();
	}

}
