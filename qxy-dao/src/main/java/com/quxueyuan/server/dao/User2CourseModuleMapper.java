package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.UserJoin.User2CourseModule;
import com.quxueyuan.bean.vo.req.CourseModuleParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface User2CourseModuleMapper {

    @Select("select * from user2coursemodule where userid=#{uid} and skuid=#{skuId} and courseid=#{courseId} and moduleid=#{moduleId} and is_delete=0")
    User2CourseModule selectUser2CourseModule(Integer uid, Integer moduleId, Integer courseId, Integer skuId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user2coursemodule (userid,is_delete,create_date,courseid,update_date,status,moduleid,skuid) values(#{uid},0,now(),#{courseId},now(),0,#{moduleId},#{skuId})")
    int insertUser2CourseModule(CourseModuleParam courseModuleParam);

    @Select("select * from user2coursemodule where userid=#{uid} and skuid=#{skuId} and moduleid=#{fristModuleId} and courseid=#{courseId} and is_delete=0")
    User2CourseModule selectUser2CourseFirstModule(Integer uid, Integer courseId, Integer fristModuleId, Integer skuId);

    @Update("update user2coursemodule set status=1 where userid=#{uid} and skuid=#{skuId} and moduleid=#{moduleId} and courseid=#{courseId} and is_delete=0")
    int updateUser2CourseModule(Integer uid, Integer courseId, Integer moduleId, Integer skuId);

    @Select("select * from user2coursemodule where userid=#{uid} and skuid=#{skuId} and is_delete=0 order by id")
    List<User2CourseModule> selectUser2CourseModuleList(Integer uid, Integer skuId);
}
