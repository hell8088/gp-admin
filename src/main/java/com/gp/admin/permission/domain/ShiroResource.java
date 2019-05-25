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
@Comment("资源表")
@Entity(name="shiro_resource")
public class ShiroResource extends Domain{
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Comment(value = "父节点")
	@Column(name = "pid")
	private long pid;
	
	@Comment(value = "节点级别")
	@Column(name = "level")
	private String level;
	
	@Comment(value = "资源名称")
	@Column(name = "name")
	private String name;
	
	@Comment(value = "资源地址")
	@Column(name = "address")
	private String resAddress;
	
	@Comment(value = "资源描述")
	@Column(name = "res_desc")
	private String resDesc;
	
	@Comment(value = "资源类型")
	@Column(name = "res_type")
	private Integer resType;
	
	@Comment(value = "资源代码")
	@Column(name = "code")
	private String resCode;
	
	@Comment(value = "扩展1")
	@Column(name = "ext1")
	private String ext1;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResAddress() {
		return resAddress;
	}

	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

}
