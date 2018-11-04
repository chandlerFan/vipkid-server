package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.SubLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubLevelMapper {

    @Select("select * from sublevel where level_id=#{levelId} and is_delete=0 and status=0 order by id")
    List<SubLevel> seleteSubLevelByLevelId(Integer levelId);

    @Select("select * from sublevel where id=#{subLevelId}")
    SubLevel findById(Integer subLevelId);
}
