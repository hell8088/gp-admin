package com.gp.admin.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 上午9:31:52
 */
public class FirstInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(FirstInterceptor.class);

	// 在请求处理之前进行调用(Controller方法调用之前)
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {

		logger.info("zzz invoke FirstInterceptor preHandle");
		return true; // 如果false，停止流程，api被拦截
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		//logger.info("zzz invoke FirstInterceptor postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		//logger.info("zzz invoke afterCompletion postHandle");
	}

}
