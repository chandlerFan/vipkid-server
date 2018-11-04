package com.quxueyuan.bean.vo.req;

import lombok.Data;

/**
 *
 * liuwei
 * 2018-10-22
 */
@Data
public class UserRegisterParam {
	
	private String password;
	private String phone;
	private String openId;
	private Integer source;//注册来源，只记第一次0 服务号  1 小程序 2 APP

	private String name;
	private String pic;
	private Integer sex;//性别  0 男  1女

}
