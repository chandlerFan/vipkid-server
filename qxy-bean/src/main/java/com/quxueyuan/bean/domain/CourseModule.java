package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

@Data
public class CourseModule {
	
	private Integer id;
	private String name;
	private Integer isDelete;
	private Date createDate;
	private Date updateDate;
	private Integer status;
	private Integer sort;
	private Integer skuId;
	
}
