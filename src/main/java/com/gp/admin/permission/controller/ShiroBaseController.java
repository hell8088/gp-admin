package com.gp.admin.permission.controller;

import com.gp.admin.permission.PremissionConstant;

/**
 * 
 * @author wangjiehan
 *
 */
public class ShiroBaseController {

	public static String getCurrentUser() {
		return PremissionConstant.DEFAULT_USER;
	}
	
}
