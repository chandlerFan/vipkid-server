package com.quxueyuan.bean.vo.res;

import lombok.Data;

@Data
public class PoemRecicationVo {
	
	private Integer user2reciteId;
	private Integer uid;
	//0解锁  1 锁定
    private Integer rStatus;
    private Integer courseId;
    private String audioFull;
    private Integer dStatus;
    private Integer orderIdx;
    private String audioUrl;
 
}
