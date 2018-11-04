package com.quxueyuan.bean.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TestChoise {
	
	private Integer id;
	private Integer isDelete;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
	private Date createDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateDate;
    private Integer status;
    private String answer;
    private Integer courseid;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer sort;
    private String question;
    private String audio;
    private String description;
   
}
