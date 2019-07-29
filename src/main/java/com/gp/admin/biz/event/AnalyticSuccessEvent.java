package com.gp.admin.biz.event;

import org.springframework.context.ApplicationEvent;
/**
 * 定义 AnalyticSuccessEvent 类型事件
 *
 * @author: wangjiehan
 * @date:   2019年7月10日 上午10:08:33
 */
public class AnalyticSuccessEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private AnalyticResult result;
	
	public AnalyticResult getResult() {
		return result;
	}

	public AnalyticSuccessEvent(Object source, String msg,AnalyticResult result) {
		super(source);
		this.result = result;
	}

}
