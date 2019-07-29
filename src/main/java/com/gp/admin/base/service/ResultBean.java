package com.gp.admin.base.service;

import com.gp.admin.base.domain.ResultData;

/**
 *
 * @author: wangjiehan
 * @date: 2019年7月16日 下午5:59:14
 */
public class ResultBean {

	public static <T> ResultData<T> successBuild(T t, long count) {
		ResultData<T> r = new ResultData<T>();
		r.setCode(0);
		r.setMsg("success");
		r.setData(t);
		r.setCount(count);
		return r;
	}

}
