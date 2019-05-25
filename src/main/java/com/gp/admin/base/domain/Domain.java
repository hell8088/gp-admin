package com.gp.admin.base.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 
 * @author wangjiehan
 *
 */
@MappedSuperclass
public abstract class Domain implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	/** 主键. */
	@Id
	@Comment("主键")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("创建人")
	@Column(length = 50)
	private String creater;
	
	@Comment("修改人")
	@Column(length = 50)
	private String modifier;

	/** 创建时间. */
	@Comment("创建时间")
	@Column(name = "created_date")
	private Date createdDate;

	/** 修改时间. */
	@Comment("修改时间")
	@Column(name = "modified_date", columnDefinition = " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate;

	/** 数据状态. */
	@Comment("数据状态 1:已删除；0：未删除")
	private Integer deleted;

	/**
	 * 返回 主键.
	 *
	 * @return 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 主键.
	 *
	 * @param id
	 *            新的 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 创建时间.
	 *
	 * @return 创建时间
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * 设置 创建时间.
	 *
	 * @param createdDate
	 *            新的 创建时间
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * 返回 修改时间.
	 *
	 * @return 修改时间
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * 设置 修改时间.
	 *
	 * @param modifiedDate
	 *            新的 修改时间
	 */

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setCreated_date(Date d) {
		this.setCreatedDate(d);
	}

	public void setModified_date(Date d) {
		this.setModifiedDate(d);
	}

	public Date getCreated_date() {
		return this.getCreatedDate();
	}

	public Date getModified_date() {
		return this.getModifiedDate();
	}
}
