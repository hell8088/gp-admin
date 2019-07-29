package com.gp.admin.common.sftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月4日 下午4:38:04
 */
@Component
public class GeneralSFTPClient implements SFTPClient {

	private static final Logger logger = LoggerFactory.getLogger(GeneralSFTPClient.class);

	private Session session;
	private ChannelSftp channel;
	private SFTPConfig config;

	public GeneralSFTPClient(SFTPConfig sFTPConfig) {
		this.config = sFTPConfig;
	}

	@Override
	public boolean login() {
		logger.info("zzz sftp start login");
		logger.info("sftp userName is : " + config.getUserName());
		try {
			JSch jsch = new JSch();
			this.session = jsch.getSession(config.getUserName(), config.getHost(), config.getPort());

			if (!StringUtils.isEmpty(config.getPassword())) {
				this.session.setPassword(config.getPassword());
			} else if (!StringUtils.isEmpty(config.getRasPath())) {
				URL pubKeyPath = this.getClass().getResource(config.getRasPath());
				logger.info("pubKeyPath is : " + pubKeyPath.getPath());
				jsch.addIdentity(pubKeyPath.getPath());
			} else {
				logger.error("zzz password or rsaFile is not exists");
			}

			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", SFTPConfig.STRICT_HOSTKEY_CHECKING);
			prop.put("PreferredAuthentications", SFTPConfig.PREFERRED_AUTHENTICATIONS);
			this.session.setConfig(prop);
			this.session.setTimeout(config.getTimeOut());
			this.session.connect();

			logger.info("zzz opening channel");
			this.channel = (ChannelSftp) session.openChannel("sftp");
			this.channel.connect();

			logger.info("zzz sftp connected successfully");
			return true;
		} catch (JSchException e) {
			logger.error("zzz sftp login failed", e);
			return false;
		}
	}

	@Override
	public void logout() {
		if (channel != null) {
			channel.quit();
			channel.disconnect();
		}
		if (session != null) {
			session.disconnect();
		}
	}

	@Override
	public void upload(String localPath, String sftpPath) {
		try {
			channel.put(localPath, sftpPath);
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void download(String sftpPath, String localPath) throws IOException {
		File file = new File(localPath);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			channel.get(sftpPath, fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	@Override
	public boolean exists(String path) {
		try {
			channel.ls(path);
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void rename(String filePath1, String filePath2) {
		try {
			channel.rename(filePath1, filePath2);
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}

}
