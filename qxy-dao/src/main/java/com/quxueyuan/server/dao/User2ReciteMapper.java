package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.UserJoin.User2Recite;
import com.quxueyuan.bean.domain.UserJoin.User2ReciteData;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface User2ReciteMapper {

    @Select({"<script>"+
            "select \n" +
            "r.id user2reciteId,\n" +
            "r.userid uid,\n" +
            "r.status rStatus,\n" +
            "r.courseid courseId,\n" +
            "r.audio_full audioFull,\n" +
            "d.status dStatus,\n" +
            "d.order_idx orderIdx,\n" +
            "d.audio_url audioUrl\n" +
            "from user2recite r \n" +
            "left join user2recite_data d on r.id = d.user2recite_id\n" +
            "where 1 = 1 \n" +
            "and r.is_delete = 0\n" +
            "and d.is_delete = 0\n" +
            "<when test='user2reciteId != null'>\n" +
            "AND r.id = #{user2reciteId}\n" +
            "</when>\n" +
            "<when test='user2reciteDataId != null'>\n" +
            "AND d.id = #{user2reciteDataId}\n" +
            "</when>\n" +
            "order by d. order_idx"+
            "</script>"})
    List<Map> getAuditData(Integer user2reciteId, Integer user2reciteDataId);

    @Select("select * from user2recite ur \n" +
            "where ur.userid = #{uid}\n" +
            "and ur.courseid = #{courseId}")
    User2Recite findByParam(Integer uid, Integer courseId);

    @Select("SELECT * FROM user2recite_data u join poemsku_detail_data p\n" +
            "on u.poemsku_detail_data_id = p.id\n" +
            "where u.user2recite_id = #{id} order by u.order_idx  ")
    List<User2ReciteData> findDataByParam(Integer id);

    @Select("select * from user2recite ur \n" +
            "where ur.userid = #{userid} \n" +
            "and ur.courseid = #{courseid} " +
            "and ur.poemsku_detail_id = #{poemskuDetailId}")
    User2Recite findByUidAndCourseIdAndPoemskuDetailId(User2Recite user2Recite);

    @Insert("INSERT INTO user2recite(`userid`, `is_delete`, `create_date`, `update_date`, `status`, `courseid`, `audio_full`, `poemsku_detail_id`) \n" +
            "VALUES (#{userid}, 0, now(), now(), #{status}, #{courseid}, #{audioFull}, #{poemskuDetailId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User2Recite user2Recite);

    @Update("update user2recite set audio_full = #{audioFull}, update_date = now() where id = #{id}")
    void update(User2Recite user2Recite);

    @Select("select * from user2recite_data \n" +
            "where user_id = #{userId} and course_id = #{courseId} \n" +
            "and user2recite_id = #{user2reciteId} and poemsku_detail_data_id = #{poemskuDetailDataId}")
    User2ReciteData findUser2ReciteData(User2ReciteData user2ReciteData);

    @Update("update user2recite_data set audio_url = #{audioUrl}, update_date = now() where id = #{id}")
    void updateUser2ReciteData(User2ReciteData ud_);

    @Insert("INSERT INTO user2recite_data (`user_id`, `is_delete`, `create_date`, `update_date`, `status`, `course_id`, `user2recite_id`, `audio_url`, `poemsku_detail_data_id`) \n" +
            "VALUES (#{userId}, 0, now(), now(), #{status}, #{courseId}, #{user2reciteId}, #{audioUrl}, #{poemskuDetailDataId})")
    void insertUser2ReciteData(User2ReciteData user2ReciteData);
}
