package com.quxueyuan.server.dao;

import com.github.pagehelper.Page;
import com.quxueyuan.bean.domain.PoemskuDetail;
import com.quxueyuan.bean.domain.PoemskuDetailData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PoemskuDetailMapper {

    @Select("select \n" +
            "p.course_id,  \n" +
            "p.is_delete,  \n" +
            "p.create_date,  \n" +
            "p.update_date,  \n" +
            "p.id,\n" +
            "p.name,\n" +
            "p.status,\n" +
            "p.full_name,\n" +
            "p.author,\n" +
            "p.dynasty,\n" +
            "p.content,\n" +
            "p.pic_content,\n" +
            "p.audio_spell,\n" +
            "p.audio_full,\n" +
            "p.pic_spell,\n" +
            "p.pic_famousteacher,\n" +
            "p.audio_famousteacher\n" +
            "from poemsku_detail p \n" +
            "left join user2course uc on p.course_id = uc.courseid \n" +
            "where uc.userid = #{uid}\n" +
            "and p.course_id = #{courseId}\n" +
            "and uc.is_delete = 0\n" +
            "and p.is_delete = 0\n")
    PoemskuDetail getPoemskuDetail(Integer uid, Integer courseId);

    @Select("select id, course_id, poemsku_detail_id, is_delete, create_date, update_date, status, order_idx, text, audio from poemsku_detail_data where poemsku_detail_id = #{poemskuDetailId} order by order_idx")
    List<PoemskuDetailData> getPoemskuDetailData(Integer poemskuDetailId);

    @Select("select id, course_id, poemsku_detail_id, is_delete, create_date, update_date, status, order_idx, text, audio from poemsku_detail_data order by order_idx")
    Page<PoemskuDetailData> findList();
    @Select("select * from poemsku_detail where course_id = #{courseId} and is_delete=0 and status=0")
    PoemskuDetail getPoemskuDetailByCourseId(PoemskuDetail poemskuDetail);
}
