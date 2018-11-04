package com.quxueyuan.bean.vo.req;

import lombok.Data;

import java.io.File;

/**
 * 提交音频文件入参
 * liuwei
 * 2018-10-22
 */
@Data
public class CommitReciteParam {
	
	private Integer uid;
	private Integer courseId;
	private Integer testReciteId; //朗诵题id
	private String recordingAdd;  //录音
	private Integer trainingcampId;
	private Integer skuId;
	private Integer moduleId;
	private String type;// WX 、APP
	
	//type == WX
	private String openId;
	private String resourcesUrl; //微信资源路径

	//type == APP
	private File file;
	
	


}
