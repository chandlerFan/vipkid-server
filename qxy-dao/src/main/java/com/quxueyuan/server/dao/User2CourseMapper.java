package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.TestResult;
import com.quxueyuan.bean.domain.UserJoin.User2Course;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface User2CourseMapper {
    @Select("select * from user2course where userid=#{userId}")
    List<User2Course> selectUser2CourseByUserId(Integer userId);

    @Select("select * from user2course where userid=#{userId} and courseid=#{courseId} and trainingcampid=#{trainingCampId}")
    User2Course selectUser2CourseByUserIdAndCourseId(Integer userId, Integer courseId, Integer trainingCampId);

    @Select("select \n" +
            "u.name,\n" +
            "u.pic,\n" +
            "k.choise_test_name,\n" +
            "k.recite_test_name,\n" +
            "c.score_choise ,\n" +
            "c.score_recite ,\n" +
            "c.score_all ,\n" +
            "c.courseid courseId,\n" +
            "c.star_level starLevel\n" +
            "from user2course c\n" +
            "LEFT join user u on c.userid = u.id\n" +
            "left join course co on c.courseid = co.id\n" +
            "left join sku k on co.sku_id = k.id\n" +
            "where 1 = 1 \n" +
            "and c.is_delete = 0\n" +
            "and u.is_delete = 0\n" +
            "and userid = #{uid}\n" +
            "and courseid = #{courseId}")
    TestResult getTestresultreport(Integer uid, Integer courseId);



    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user2course (userid,is_delete,create_date,update_date,status,courseid,score_choise,score_recite,score_all,star_level,trainingcampid)values(#{userId},#{isDelete},NOW(),NOW(),#{status},#{courseId},#{scoreChoise},#{scoreRecite},#{scoreAll},#{starLevel},#{trainingcampid})")
    int insertUser2Course(User2Course user2Course);


    @Update("update user2course set update_date=now() ,score_choise=#{scoreChoise} , score_recite=#{scoreRecite}  , score_all=#{scoreAll} , status=#{status}  where id=#{id}")
    int updateUser2CourseScore(User2Course user2Course);


    @Select("SELECT COUNT(*) from user2course u LEFT  JOIN course c on  c.id=u.courseid where c.type=#{type} and u.userid=#{uid} and u.trainingcampid=#{trainingCampId} and c.sublevel_id=#{subLevel} and c.level_id=#{levelId} and u.status=3 and u.is_delete=0")
    Integer selectUserJoinCourseCount(Integer uid, Integer trainingCampId, Integer levelId, Integer subLevel, Integer type);


    @Update("update user2course set status = #{status}, update_date = now() where id = #{courseId}")
    void updateCouresStatus(Integer courseId, Integer status);

    @Select("SELECT\n" +
            "\tc.id cid, uc.id ucid, uc.status\n" +
            "FROM\n" +
            "\tcourse c left join user2course uc on c.id = uc.courseid\n" +
            "\twhere c.trainingcamp_id = #{trainingcampId} \n" +
            "\tAND c.sku_id = #{skuId} \n" +
            "\tAND c.level_id = #{levelId} \n" +
            "\tAND c.sublevel_id = #{sublevelId} \n" +
            "\tAND c.week_course_id = #{weekCourseId} \n" +
            "\tAND c.STATUS = 0 \n" +
            "\tAND c.is_delete = 0 order by sort desc")
    List<Map<String, Object>> selectUser2CourseByWeek(Integer trainingcampId, Integer skuId, Integer levelId, Integer sublevelId, Integer weekCourseId);

    @Update("update user2course set star_level = #{starLevel}, update_date = now() where id = #{id}")
    void updateCourseStarLevel(User2Course user2Course);


    @Update("update user2course set update_date=now() ,score_choise=#{scoreChoise} , score_recite=#{scoreRecite}  , score_all=#{scoreAll} , status=#{status}  where userid=#{userId} and courseid=#{courseId} and trainingcampid=#{trainingcampid}")
    int updateUserCourseScoreAndStatus(User2Course user2Course);
}
