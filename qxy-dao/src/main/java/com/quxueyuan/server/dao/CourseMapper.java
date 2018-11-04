package com.quxueyuan.server.dao;


import com.quxueyuan.bean.domain.Course;
import com.quxueyuan.bean.vo.req.CourseParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select * from course where trainingcamp_id=#{trainingCampId}  and sku_id=#{skuId} and  level_id=#{levelId} and status=0 and is_delete=0 order by sort desc")
    List<Course> getCourseList(CourseParam courseParam);

    @Select("select * from course where id=#{courseId} and status=0 and is_delete=0")
    Course getCourseByCourseId(Integer courseId);

    
    @Select("select * from course where trainingcamp_id=#{trainingCampId}  and sku_id=#{skuId} and  level_id=#{levelId}  and sublevel_id=#{subLevelId}  and status=0 and is_delete=0 order by sort desc")
    List<Course> getSublevelCourseList(CourseParam courseParam);

    @Select("select * from course where trainingcamp_id=#{trainingCampId}  and sku_id=#{skuId} and  level_id=#{levelId}  and sublevel_id=#{subLevelId} and type=#{type} and status=0 and is_delete=0")
    Course selectCouseByType(Integer trainingCampId, Integer levelId, Integer subLevelId, Integer type, Integer skuId);
}
