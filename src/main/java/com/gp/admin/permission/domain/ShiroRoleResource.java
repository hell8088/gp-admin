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
@Entity(name="shiro_role_resource")
public class ShiroRoleResource extends Domain{

	@Transient
	private static final long serialVersionUID = 1L;

	@Comment(value = "资源Id")
	@Column(name = "res_id")
	private Long resId;
	
	@Comment(value = "角色Id")
	@Column(name = "role_id")
	private Long roleId;

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
