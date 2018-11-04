package com.quxueyuan.bean.domain.UserJoin;

import lombok.Data;

import java.util.Date;


@Data
public class User2TestRecite {
	
	
	private Integer id;
	private Integer userid;
	private Integer isDelete;
	private Date createDate;
	private Date updateDate;
	private Integer status;
	private String recordingAdd;
	private Integer testReciteId;
	private Integer courseId;
	private Integer testScore;

}
