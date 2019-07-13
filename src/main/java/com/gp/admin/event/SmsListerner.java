package com.gp.admin.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 实现ApplicationListener监听器，并监听 AnalyticSuccessEvent 类型事件
 * @author: wangjiehan
 * @date: 2019年7月9日 下午7:05:32
 */
@Component
public class SmsListerner implements ApplicationListener<AnalyticSuccessEvent> {

	@Override
	public void onApplicationEvent(AnalyticSuccessEvent event) {
		System.out.println("短信 ： " + event.getResult().getResultMsg());
	}

}
