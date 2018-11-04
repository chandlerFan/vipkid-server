package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.User;
import com.quxueyuan.bean.domain.UserLogin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where id=#{userId}")
    User getUserInfoById(Integer userId);
    
    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);

   
    @Update("update user set name=#{name} , sex=#{sex}  , pic=#{pic} where id=#{id}")
    int updateUser(User user);

    @Insert("insert into user (name,is_delete,create_date,update_date,pic,status,sex)values(#{name},0,NOW(),NOW(),#{pic},0,#{sex})")
    int insertUser(User user);

    @Insert({"insert into user_login (is_delete,create_date,update_date,status,phone, password,open_id,unionid,source)" +
            "values(0,now(),now(),#{status},#{phone},#{password},#{openId},#{unionid},#{source})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUserLogin(UserLogin userLogin);

    @Select("select * from user_login where phone = #{phone}")
    UserLogin findUserLoginByPhone(String phone);

    @Insert("insert into user (name,is_delete,create_date,update_date,pic,status,sex,uid)values(#{name},0,NOW(),NOW(),#{pic},0,#{sex},#{uid})")
    int insertRegisterUser(User user);

    @Select("select * from user_login where phone = #{phone} and password = #{password}")
    UserLogin findUserLogin(UserLogin userLogin);

    @Select("select * from user where uid=#{uid}")
    User findUserByUid(Integer uid);

    @Select("select * from user_login where open_id=#{openid} and is_delete=0 and status=0")
    UserLogin selectUserByOpenid(String openid);
}
