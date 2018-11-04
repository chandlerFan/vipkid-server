package com.quxueyuan.bean.vo.req;

import lombok.Data;

/**
 * 古诗sku-古诗朗诵页获取试听录音接口入参
 * liuwei
 * 2018-10-22
 */
@Data
public class AuditionrecitationParam {
	
	private Integer uid;
	private Integer user2reciteId; //用户参课程模块古诗朗诵ID
	private Integer user2reciteDataId; //用户参课程模块古诗朗诵音频数据地址ID

}
