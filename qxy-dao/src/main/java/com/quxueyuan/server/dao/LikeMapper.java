package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.signup.User2like;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    @Insert("insert into user2like (is_delete,create_date,update_date,status,uid_from,uid_to,course_id,sku_id,like_date) values(0,NOW(),NOW(),0,#{uidFrom},#{uidTo},#{courseId},#{skuId},NOW())")
    int insertUser2Like(User2like user2like);
}
