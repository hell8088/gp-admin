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
@Comment("角色表")
@Entity(name="shiro_role")
public class ShiroRole extends Domain{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Comment(value = "角色名")
	@Column(name = "role_name")
	private String roleName;
	
	@Comment(value = "角色描述")
	@Column(name = "role_desc")
	private String roleDesc;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
}
