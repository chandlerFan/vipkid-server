package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.UserJoin.User2TrainingCamp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface User2TrainingCampMapper {

    @Insert("insert into user2trainingcamp (userid,trainingcamp_id,trainingcamp_name,create_date,update_date,is_delete,status,level_id,level_name) values(#{userId},#{trainingCampId},#{trainingCampName},NOW(),NOW(),#{isDelete},#{status},#{levelId},#{levelName})")
    int insertUser2TrainingCamp(User2TrainingCamp user2TrainingCamp);

    @Select("select * from user2trainingcamp where userid=#{uid} and trainingcamp_id=#{trainingCampId} and level_id=#{levelId} and sublevel_id=#{sublevelId} and status=0 and is_delete=0")
    User2TrainingCamp selectUser2TrainingCampByUidAndTrainingCampId(Integer uid, Integer trainingCampId, Integer levelId, Integer sublevelId);

    @Select("select * from user2trainingcamp where userid=#{uid} and trainingcamp_id=#{trainingCampId} and level_id = #{levelId} and status=0 and is_delete=0")
    User2TrainingCamp findUser2Training(Integer uid, Integer trainingCampId, Integer levelId);

    @Update("update user2trainingcamp \n" +
            "set sublevel_id = #{sublevelId},\n" +
            "sublevel_name = #{sublevelName},\n" +
            "update_date = now()\n" +
            "WHERE id = #{id}")
    void updateSubLevel(User2TrainingCamp user2TrainingCamp);

    @Select("select * from user2trainingcamp where userid=#{userId} and trainingcamp_id=#{trainingCampId} and is_delete=0")
    User2TrainingCamp findUser2TrainingByUidAndTrainingCampId(User2TrainingCamp user2TrainingCamp);

    @Select("select * from user2trainingcamp where " +
            "userid=#{userId} and trainingcamp_id=#{trainingCampId} " +
            "and level_id=#{levelId} " +
            "and status=0 and is_delete=0")
    User2TrainingCamp find(User2TrainingCamp ut);

    @Update("update user2trainingcamp \n" +
            "set status = #{status},\n" +
            "update_date = now()\n" +
            "WHERE id = #{id}")
    void updateStatus(User2TrainingCamp user2TrainingCamp);

}
