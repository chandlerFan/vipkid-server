package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Subject {
	
	private Integer id;
	private String name;
	private Integer isDelete;
	private Date createDate;
	private Date updateDate;
	private Integer status;

}
