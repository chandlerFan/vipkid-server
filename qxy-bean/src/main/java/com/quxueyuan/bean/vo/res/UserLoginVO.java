package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable {
    
	private String phone;
    private Integer status;
    private String openId;
    private Integer source;
    private Integer uid;
    private String name;
    private Integer sex;
    private String pic;
}
