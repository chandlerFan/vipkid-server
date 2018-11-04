package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.UserJoin.User2TestRecite;
import com.quxueyuan.bean.vo.req.CourseIdParam;
import com.quxueyuan.bean.vo.res.TestReciteVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface User2TestReciteMapper {

    @Insert("insert into user2test_recite (userid,is_delete,create_date,update_date,status,recording_add,test_recite_id,course_id,test_score)values(#{userid},0,NOW(),NOW(),#{status},#{recordingAdd},#{testReciteId},#{courseId},#{testScore})")
    int insertUser2TestRecite(User2TestRecite user2TestRecite);


    @Select("select * from user2test_recite where userid=#{userid} and test_recite_id=#{testReciteId} and course_id=#{courseId}")
    User2TestRecite selectUser2TestRecite(User2TestRecite user2TestRecite);


    @Update("update user2test_recite set status=#{status} , test_score=#{testScore} where userid=#{userid} and test_recite_id=#{testReciteId} and course_id=#{courseId}")
    int updateUser2TestRecite(User2TestRecite user2TestRecite);

    @Update("update user2test_recite set recording_add=#{recordingAdd} , update_date=NOW() ,test_score=#{testScore} where userid=#{userid} and test_recite_id=#{testReciteId} and course_id=#{courseId}")
    int updateUser2TestReciteAudioFull(User2TestRecite user2TestRecite);
    @Select("select \n" +
            "t.id as testreciteId,\n" +
            "t.answer as answer,\n" +
            "t.courseid as courseid,\n" +
            "t.sort as sort,\n" +
            "t.question as question,\n" +
            "t.audio_question as audioQuestion,\n" +
            "t.description as description\n" +
            "FROM  test_recite t JOIN user2test_recite u  on u.test_recite_id=t.id WHERE u.status=#{status} and u.userid=#{uid} and u.course_id=#{courseId}")
    List<TestReciteVO> selectUser2ErrorRecite(CourseIdParam courseIdParam);
}
