package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.signup.User2share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ShareMapper {

    @Insert("INSERT INTO user2share (`is_delete`, `create_date`, `update_date`, `status`, `user_id`, `description`, `course_id`, `sku_id`, `share_date`) \n" +
            "VALUES (0, now(), now(), #{status}, #{userId}, #{description}, #{courseId}, #{skuId}, #{shareDate})")
    void insert(User2share us);
}
