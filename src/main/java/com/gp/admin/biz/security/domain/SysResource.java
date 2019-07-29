package com.gp.admin.biz.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.gp.admin.base.domain.Comment;
import com.gp.admin.base.domain.Domain;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午10:25:53
 */

@Comment("资源管理")
@Entity(name = "sys_resource")
public class SysResource extends Domain  {
	@Transient
	private static final long serialVersionUID = 1L;

	@Comment("资源名称")
	private String name;

	@Comment("资源类型")
	private ResourceType type;

	@Comment("资源路径")
	private String url;

	@Comment("资源码")
	private String permission;

	@Comment("父编号")
	@Column(name = "parent_id")
	private Long parentId;

	@Comment("父编号列表")
	@Column(name = "parent_ids")
	private String parentIds;

	@Comment("图标样式")
	@Column(name = "icon")
	private String icon;

	@Comment("优先级")
	@Column(name = "priority")
	private Integer priority;

	@Comment("是否可用")
	@Column(name = "available")
	private Boolean available;

	public static enum ResourceType {
		menu("菜单"), button("按钮"), externalLink("外部链接");

		private final String info;

		private ResourceType(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
