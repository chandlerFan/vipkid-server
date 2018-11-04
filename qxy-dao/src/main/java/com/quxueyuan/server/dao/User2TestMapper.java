package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.UserJoin.User2TestChoise;
import com.quxueyuan.bean.domain.UserJoin.User2TestRecite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface User2TestMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user2test_choise (userid,is_delete,update_date,create_date,status,test_choise_id,course_id,answer,test_score)values(#{uid},#{isDelete},now(),now(),#{status},#{testChoiseId},#{courseId},#{answer},#{testScore})")
    int insertUser2CommitChoise(User2TestChoise user2TestChoise);
    
    
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user2test_choise (userid,is_delete,update_date,create_date,status,test_choise_id,course_id,answer,test_score)values(#{uid},#{isDelete},now(),now(),#{status},#{testChoiseId},#{courseId},#{answer},#{testScore})")
    int insertUser2TestRecite(User2TestRecite user2TestRecite);
    
}
