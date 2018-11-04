package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.UserJoin.User2TestChoise;
import com.quxueyuan.bean.vo.req.CourseIdParam;
import com.quxueyuan.bean.vo.res.TestChoiseVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface User2TestChoiseMapper {
    @Select("select * from user2test_choise where userid=#{userid} and test_choise_id=#{testChoiseId} and course_id=#{courseId} ")
    User2TestChoise selectUser2TestChoise(User2TestChoise user2TestChoise);
    @Update("update user2test_choise set update_date=NOW(),status=#{status} , test_score=#{testScore},answer=#{answer} where userid=#{userid} and test_choise_id=#{testChoiseId} and course_id=#{courseId} ")
    int updateUser2TestChoise(User2TestChoise user2TestChoise);
    @Insert("insert into user2test_choise (userid,is_delete,create_date,update_date,status,test_choise_id,course_id,answer,test_score)values(#{userid},0,NOW(),NOW(),0,#{testChoiseId},#{courseId},#{answer},#{testScore})")
    int insertUser2TestChoise(User2TestChoise user2TestChoise);
    @Select("select \n" +
            "t.id as testchoiseId,\n" +
            "u.answer as answer,\n" +
            "u.course_id as courseId,\n" +
            "t.option_a as optionA,\n" +
            "t.option_b as optionB,\n" +
            "t.option_c as optionC,\n" +
            "t.option_d as optionD,\n" +
            "t.sort as sort,\n" +
            "t.question as question,\n" +
            "t.audio as audio,\n" +
            "t.description as description\n" +
            "FROM  test_choise t JOIN user2test_choise u  on u.test_choise_id=t.id WHERE u.status=#{status} and u.userid=#{uid} and u.course_id=#{courseId}")
    List<TestChoiseVO> selectUser2ErrorChoise(CourseIdParam courseIdParam);
}
