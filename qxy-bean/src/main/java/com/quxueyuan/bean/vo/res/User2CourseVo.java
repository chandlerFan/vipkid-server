package com.quxueyuan.bean.vo.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User2CourseVo {
	
	private Integer courseId;
	private String name;
	//0解锁  1 锁定
    private Integer status;
    private Integer sort;
    private Integer trainingCampId;
    private Integer skuId;
    private Integer levelId;
    private Integer subLevelId;
    private String subLevelName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone ="GMT+8")
    private Date studyDate;
    private Integer type;
    private int score;
    private int starLevel;
    private String unlockPic; // 解锁状态图片
    private String lockPic; // 锁状态图片
}
