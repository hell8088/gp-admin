package com.gp.admin.sftp;

import java.io.IOException;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月4日 下午2:48:13
 */
public interface SFTPClient {

	/**
	 * 
	 * @return
	 */
	boolean login();

	/**
	 * 
	 */
	void logout();

	/**
	 * 
	 * @param localPath
	 * @param sftpPath
	 */
	void upload(String localPath, String sftpPath);

	/**
	 * 
	 * @param sftpPath
	 * @param localPath
	 * @throws IOException
	 */
	void download(String sftpPath, String localPath) throws IOException ;
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	boolean exists(String path);
	
	/**
	 * 
	 * @param filePath1
	 * @param filePath2
	 */
	void rename(String filePath1, String filePath2);
}
