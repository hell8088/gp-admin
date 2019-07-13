package com.gp.admin.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月9日 下午7:10:02
 */
@Component
public class WxListerner implements ApplicationListener<AnalyticSuccessEvent> {

	@Override
	public void onApplicationEvent(AnalyticSuccessEvent event) {
		System.out.println("微信 ： " + event.getResult().getResultMsg());
	}
}
