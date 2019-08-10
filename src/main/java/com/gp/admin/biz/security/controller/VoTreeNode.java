package com.gp.admin.biz.security.controller;

/**  
 *
 * @author: wangjiehan
 * @date:   2019年7月17日 上午11:01:55   
 */
public class VoTreeNode {

	private long id;
	private long pId;
	private String name;
	private String click;
	private String url;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
