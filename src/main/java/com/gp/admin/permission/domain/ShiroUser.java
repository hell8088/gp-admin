package com.gp.admin.permission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.gp.admin.base.domain.Comment;
import com.gp.admin.base.domain.Domain;

/**
 * 
 * @author wangjiehan
 *
 */
@Comment("用户表")
@Entity(name="shiro_user")
public class ShiroUser extends Domain{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Comment(value = "用户名")
	@Column(name = "user_name")
	private String userName;
	
	@Comment(value = "密码")
	@Column(name = "password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
