package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.TestRecite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestReciteMapper {

    @Select("select \n" +
            "t.id,\n" +
            "t.answer,\n" +
            "t.courseid,\n" +
            "t.question,\n" +
            "t.description,\n" +
            "t.sort,\n" +
            "r.recording_add\n" +
            "from test_recite  t \n" +
            "left join user2test_recite r on t.id = r.test_recite_id\n" +
            "where r.userid = #{uid}\n" +
            "and r.course_id = #{courseId}\n" +
            "and t.is_delete = 0\n" +
            "and r.is_delete = 0\n" +
            "order by t.sort")
    List<TestRecite> findUser2TestReciteList(Integer uid, Integer courseId);

    @Select("select * from test_recite where id = #{testReciteId}")
    TestRecite findById(Integer testReciteId);
}
