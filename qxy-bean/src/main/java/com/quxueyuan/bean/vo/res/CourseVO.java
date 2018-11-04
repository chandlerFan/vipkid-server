package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseVO implements Serializable {
    
	private String courseName;
    private Integer type;
    private List<CourseModuleVO> courseModuleVOList;
    private PoertyContentVO poertyContentVO;
    private Integer scoreAll;
}
