package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.CourseModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseModuleMapper {
   
	@Select("select * from course_module where sku_id=#{skuId} and status=0 and is_delete=0 order by sort")
    List<CourseModule> selectCourseModuleBySkuId(Integer skuId);
    
    
    
    
}
