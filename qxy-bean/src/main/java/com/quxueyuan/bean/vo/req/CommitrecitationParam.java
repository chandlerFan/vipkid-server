package com.quxueyuan.bean.vo.req;

import lombok.Data;

import java.io.File;

/**
 * 提交音频文件入参
 * liuwei
 * 2018-10-22
 */
@Data
public class CommitrecitationParam {
	
	private Integer uid;
	private Integer courseId;
	private Integer trainingcampId;
	private Integer skuId;
	private Integer audioFull;  //最后完整音频时 传1 ,分句传0
	private Integer poemskuDetailId;//分句、整句 必须传
	private Integer poemskuDetailDataId;//分句传、整句不传
	private Integer moduleId;
	private String type;// WX 、APP
	
	//type == WX
	private String openId;
	private String resourcesUrl; //微信资源路径

	//type == APP
	private File file;
	
	


}
