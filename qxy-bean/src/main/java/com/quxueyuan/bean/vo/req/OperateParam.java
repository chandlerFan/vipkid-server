package com.quxueyuan.bean.vo.req;

import lombok.Data;

/**
 * 运营功能表 入参
 */
@Data
public class OperateParam {
	
	private Integer trainingcampId;
	private Integer skuId;
	private Integer levelId;
	private String channel;

}
