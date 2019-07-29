package com.gp.admin.base.domain;

/**  
 *
 * @author: wangjiehan
 * @date:   2019年7月16日 下午6:20:20   
 */
public class ResultData<T> {

	private Integer code;
	private String msg;
	private T data;
	private long count;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
}
