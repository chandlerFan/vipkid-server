package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.signup.Operate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OperateMapper {


    @Select("select * from operate where trainingcamp_id = #{trainingcampId} and sku_id = #{skuId} and level_id = #{levelId} and channel = #{channel} and status=0 and is_delete=0")
    Operate findByParam(Operate operate);


}
