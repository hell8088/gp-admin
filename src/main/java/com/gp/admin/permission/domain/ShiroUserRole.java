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
@Comment("用户角色关系表")
@Entity(name="shiro_user_role")
public class ShiroUserRole extends Domain{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Comment(value = "用户ID")
	@Column(name = "user_id")
	private Long userId;
	
	@Comment(value = "角色ID")
	@Column(name = "role_id")
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
