package com.quxueyuan.bean.vo.req;

import lombok.Data;

@Data
public class CourseIdParam {
	
	private Integer uid;
	private Integer courseId;
	//type=0 表示错题  1正确
	private Integer status;

}
