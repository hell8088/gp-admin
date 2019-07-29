package com.gp.admin.biz.security.controller;

import java.util.List;

/**  
 *
 * @author: wangjiehan
 * @date:   2019年7月22日 下午1:35:03   
 */
public class VoResourceTree {

	private String title;
	private long id;
	private boolean checked;
	private boolean spread;
	private List<VoResourceTree> children;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<VoResourceTree> getChildren() {
		return children;
	}

	public void setChildren(List<VoResourceTree> children) {
		this.children = children;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}
	
}
