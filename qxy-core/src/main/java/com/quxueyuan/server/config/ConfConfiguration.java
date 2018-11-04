package com.quxueyuan.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 这里添加各自 来自配置的常量，下面这个变量 是举列
 * @author majianchun
 *
 */

@Component
public class ConfConfiguration {
	
	/**
	 * 是否校验签名
	 */
	@Value("${quxueyuan.auth.checksign}")
	private boolean checkSign;

	public boolean isCheckSign() {
		return checkSign;
	}

	public void setCheckSign(boolean checkSign) {
		this.checkSign = checkSign;
	}
	
	
	

}
