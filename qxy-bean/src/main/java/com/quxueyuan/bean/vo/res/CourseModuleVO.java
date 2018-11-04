package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseModuleVO implements Serializable{

    private Integer moduleId;

    private String name;

    private Integer sort;

    private User2CourseModuleVO user2CourseModuleVO;
}
