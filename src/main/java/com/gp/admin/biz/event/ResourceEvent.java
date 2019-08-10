package com.gp.admin.biz.event;

import org.springframework.context.ApplicationEvent;

public class ResourceEvent extends ApplicationEvent {

	private String resourceKey;
	private static final long serialVersionUID = 1L;

	public ResourceEvent(Object source, String key) {
		super(source);
		this.resourceKey = key;
	}

	public String getResourceKey() {
		return resourceKey;
	}

}
