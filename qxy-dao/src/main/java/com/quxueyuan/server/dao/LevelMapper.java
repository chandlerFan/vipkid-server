package com.quxueyuan.server.dao;


import com.quxueyuan.bean.domain.Level;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LevelMapper {

    @Select("select * from level where sku_id=#{skuId} and status=0 and is_delete=0 order by id")
    List<Level> getLevelList(Integer skuId);

    @Select("select * from level where id = #{levelId} and is_delete=0")
    Level getLevelById(Integer levelId);
}
