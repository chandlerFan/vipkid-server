package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.signup.UserTeacher;
import com.quxueyuan.bean.domain.signup.UserTeacherTemp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTeacherMapper {


    @Select("select teacher_id as teacherId, count(id) as countStu from user_teacher WHERE signup_data=NOW()  group by teacher_id ORDER BY count(id)")
    List<UserTeacherTemp> selectUser2Teacher();

    @Insert("insert into user_teacher (user_id,teacher_id,signup_date,signup_time,is_delete,create_date,update_date) values(#{userId},#{teacherId},NOW(),NOW(),0,NOW(),NOW())")
    int insertUserTeacher(UserTeacher userTeacher);
}
