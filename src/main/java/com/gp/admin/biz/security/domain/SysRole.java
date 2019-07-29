package com.gp.admin.biz.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.gp.admin.base.domain.Comment;
import com.gp.admin.base.domain.Domain;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午10:26:06
 */

@Comment("角色管理")
@Entity(name = "sys_role")
public class SysRole extends Domain {

	@Transient
	private static final long serialVersionUID = 1L;

	@Comment("角色标识")
	@Column(name = "role")
	private String role;

	@Comment("角色描述")
	@Column(name = "description")
	private String description;

	@Comment("拥有的资源")
	@Column(name = "resource_ids")
	private String resourceIds;

	@Comment("是否可用")
	@Column(name = "available")
	private Boolean available;

	@Transient
	private List<Long> resources = new ArrayList<Long>();

	public SysRole() {
	}

	public SysRole(String role, String description, Boolean available) {
		this.role = role;
		this.description = description;
		this.available = available;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getResources() {
		return resources;
	}

	public void setResources(List<Long> resources) {
		this.resources = resources;
	}

	public String getResourceIds() {
		if (CollectionUtils.isEmpty(resources)) {
			return "";
		}
		StringBuilder s = new StringBuilder();
		for (Long resourceId : resources) {
			s.append(resourceId);
			s.append(",");
		}
		this.resourceIds = s.toString();
		return s.toString();
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;

		if (StringUtils.isEmpty(resourceIds)) {
			return;
		}
		String[] resourceIdStrs = resourceIds.split(",");
		for (String resourceIdStr : resourceIdStrs) {
			if (StringUtils.isEmpty(resourceIdStr)) {
				continue;
			}
			getResources().add(Long.valueOf(resourceIdStr));
		}
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
