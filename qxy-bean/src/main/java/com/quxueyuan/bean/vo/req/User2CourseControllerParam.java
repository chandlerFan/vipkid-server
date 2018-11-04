package com.quxueyuan.bean.vo.req;

import lombok.Data;

@Data
public class User2CourseControllerParam {
    private Integer uid;
    private Integer courseId;
    private Integer changeType;
    private Integer trainingCampId;
    private Integer skuId;
}

