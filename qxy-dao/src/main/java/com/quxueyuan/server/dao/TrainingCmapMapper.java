package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.TrainingCamp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrainingCmapMapper {

    @Select("select * from training_camp where status=0 and is_delete=0 order by id")
    List<TrainingCamp> getTrainingCmap();
    @Select("select * from training_camp where status=0 and is_delete=0 and id=#{trainingCampId}")
    TrainingCamp selectTrainingCampById(Integer trainingCampId);
}
