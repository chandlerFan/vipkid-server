package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Sku {
	private Integer id;
	private String name;
	private Integer isDelete;
	private Date createDate;
	private Date updateDate;
	private Integer status;
	private Integer subjectId;
	private Integer authorScore;
	private String authorTestName;
	private Integer choiseScore;
	private String choiseTestName;
	private Integer reciteScore;
	private String reciteTestName;
	private Integer choiseNums;
	private Integer reciteNums;

}
