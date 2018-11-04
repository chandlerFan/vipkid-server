package com.quxueyuan.bean.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GeneralParam{

	@NotNull(message = "用户编号不可为空")
	private Integer uid;
	@NotNull(message = "课程编号不可为空")
	private Integer courseId;
	private Integer status; //课程状态
	private Integer trainingCampId;

}
