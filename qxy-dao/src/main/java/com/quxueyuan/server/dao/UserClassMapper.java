package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.signup.UserClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserClassMapper {

    @Insert("INSERT INTO user_class (`class_id`, `user_id`, `join_date`, `is_delete`, `create_date`, `update_date`) \n" +
            "VALUES (#{classId}, #{userId}, #{joinDate}, 0, now(), now())")
    void insert(UserClass userClass);
}
