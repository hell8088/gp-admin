package com.gp.admin.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Custmoer Filter Demo
 * 
 * @author: wangjiehan
 * @date: 2019年7月15日 下午7:49:36
 */
public class SecondFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("zzz SecondFilter");
		chain.doFilter(request, response);
	}
}
