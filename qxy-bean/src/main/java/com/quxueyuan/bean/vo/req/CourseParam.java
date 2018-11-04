package com.quxueyuan.bean.vo.req;

import lombok.Data;

@Data
public class CourseParam {
	
	private Integer trainingCampId;
	private Integer skuId;
	private Integer uid;
	private Integer levelId;
	private Integer subLevelId;//升级到的subLevelId
}
