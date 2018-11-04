package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.util.List;

@Data
public class User2CourseListWrapper {
	
	private List<User2CourseVo> user2CourseVoList;
	private Integer subLevelId; //当前显示的subLevelId
	private Integer factSubLevelId; //库里实际的subLevelId
	private Integer nextSubLevelId; //当前级别的下一subLevelId
	private List<SubLevelVO> subLevelVOList;
//	private LevelJump levelJump; //level跳转信息

//	@Data
//	public static class LevelJump {
//
//		private Integer currentSubLevelId;//当前SublevelId
//		private String currentSubLevelName;//当前SublevelName
//		@JsonFormat(pattern="yyyy-MM-dd",timezone ="GMT+8")
//		private Date currentSubLevelStartDate;//当前Sublevel开始日期
//		@JsonFormat(pattern="yyyy-MM-dd",timezone ="GMT+8")
//		private Date currentSubLevelEndDate;//当前Sublevel结束日期
//		private Integer status; //0可跳转，1不可跳转   //是否可跳转状态
//		private Integer nextSubLevelId;//下一SublevelId
//		private String name;//下一SublevelName
//		@JsonFormat(pattern="yyyy-MM-dd",timezone ="GMT+8")
//		private Date studyDate;//下一Sublevel开始日期
//		@JsonFormat(pattern="yyyy-MM-dd",timezone ="GMT+8")
//		private Date nextSubLevelEndDate;//下一Sublevel结束日期
//	}
 
}
