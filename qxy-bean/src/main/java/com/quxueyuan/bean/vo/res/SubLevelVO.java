package com.quxueyuan.bean.vo.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL) //属性值为空则不返回
@Data
public class SubLevelVO {
	
	private Integer subLevelId;
	private String name;
	//0代表解锁，1代表不解锁 2、可解锁状态
	private int status;
	@JsonFormat(pattern="yyyy-MM-dd",timezone ="GMT+8")
	private Date date; // 可解锁时间

}
