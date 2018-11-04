package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SkuMapper {
    @Select("select * from sku where id=#{skuId} and is_delete=0 and status=0")
    Sku slectSkuById(Integer skuId);
}
