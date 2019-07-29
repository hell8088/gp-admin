package com.gp.admin.base.dao;

/**  
 *
 * @author: wangjiehan
 * @date:   2019年7月23日 下午3:15:37   
 */
public enum DataStatus {

	ACTIVE(0),
	DELETED(1);
	
	private Integer dataStatus;

	private DataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getCode() {
		return dataStatus;
	}
}
