package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
	private Integer id;
	private String name;
	private Integer isDelete;
	private Date createDate;
    private Date updateDate;
    private Integer status;
    private Integer sort;
    private Integer trainingCampId;
    private String description;
    private Integer levelId;
    private String levelName;
    private Integer  subLevelId;
    private String subLevelName;
    private Date studyDate;
    private Integer type;
    private Integer skuId;
	private String unlockPic; // 解锁状态图片
    private String lockPic; // 锁状态图片
}
