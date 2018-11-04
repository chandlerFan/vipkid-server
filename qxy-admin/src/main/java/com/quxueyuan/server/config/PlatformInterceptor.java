/** 
 * Project Name: fileservice_project 
 * File Name: FileInterceptor.java 
 * Package Name: com.huifenqi.file 
 * Date: 2015年12月30日下午2:13:24 
 * Copyright (c) 2015, www.huizhaofang.com All Rights Reserved. 
 * 
 */
package com.quxueyuan.server.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: PlatformInterceptor date: 2017年8月15日 下午2:13:24 Description: 拦截器
 * 
 * @author arison
 * @version
 * @since JDK 1.8
 */
@Component
public class PlatformInterceptor implements HandlerInterceptor {

	private static final Log logger = LogFactory.getLog(PlatformInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Accept,Accept-Encoding,Accept-Language,Connection,Content-Length," +
				"Content-Type,device-info,Host,Origin,Referer,X-Requested-With,User-Agent,Cookie");
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
		// TODO 在这里将access日志格式化输出，为的是能够全面掌控访问日志的格式
		logger.debug("Cache the request, thread id=" + Thread.currentThread().getId());
		
		//加入验签，鉴权逻辑
		//todo
		
		
		return true;
		


	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	
	}



}
