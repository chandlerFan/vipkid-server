package com.quxueyuan.server.api.service;


import com.quxueyuan.bean.domain.CourseModule;

import java.util.List;

public interface CourseModuleService {

    List<CourseModule> getCourseModule(Integer skuId);
    
    
}
