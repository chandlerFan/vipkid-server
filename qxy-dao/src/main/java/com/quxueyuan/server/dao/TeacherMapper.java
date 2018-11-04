package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.signup.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper {
    @Select("select id from teacher")
    List<Integer> selectListTeacher();

    @Select("select * from teacher where id={teacherId}")
    Teacher selectTeacherById(Integer teacherId);

}
