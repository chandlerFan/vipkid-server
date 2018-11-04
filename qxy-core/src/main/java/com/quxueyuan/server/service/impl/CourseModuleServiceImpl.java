package com.quxueyuan.server.service.impl;

import com.quxueyuan.bean.domain.CourseModule;
import com.quxueyuan.server.api.service.CourseModuleService;
import com.quxueyuan.server.dao.CourseModuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseModuleServiceImpl implements CourseModuleService {

    @Resource
    CourseModuleMapper courseModuleMapper;

    @Override
    public List<CourseModule> getCourseModule(Integer skuId) {
        List<CourseModule> courseModuleList=courseModuleMapper.selectCourseModuleBySkuId(skuId);
        return courseModuleList;
    }
    
    
    
}
