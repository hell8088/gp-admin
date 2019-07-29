package com.gp.admin.biz.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.gp.admin.base.domain.Comment;
import com.gp.admin.base.domain.Domain;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午10:21:06
 */

@Comment("用户管理")
@Entity(name = "sys_user")
public class SysUser extends Domain {

	@Transient
	private static final long serialVersionUID = 1L;

	@Comment("用户名")
	private String username;

	@Comment("密码")
	private String password;

	@Comment("角色列表")
	@Column(name = "role_ids")
	private String roleIds;

	@Comment("是否可用")
	@Column(name = "locked")
	private Boolean locked;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	
}
