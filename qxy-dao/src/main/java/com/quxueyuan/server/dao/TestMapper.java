package com.quxueyuan.server.dao;

import com.quxueyuan.bean.domain.TestChoise;
import com.quxueyuan.bean.domain.TestRecite;
import com.quxueyuan.bean.vo.req.CourseIdParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {

  
	@Select({"<script>select * from test_choise \n" +
			"where courseid = #{courseId}  \n" +
			"<when test='status != null'>\n" +
			"and status = #{status}  \n" +
			"</when>\n" +
			"and is_delete = 0\n" +
			"order by sort </script>"})
	 List<TestChoise> getTestChoiseList(CourseIdParam courseIdParam);
	
	 @Select("select * from test_choise where courseid=#{courseId} and status=0 and is_delete=0 order by sort ")
	 List<TestChoise> getTestChoiseListId(Integer courseId);
	
	 @Select({"<script>select * from test_recite \n" +
			 "where courseid = #{courseId}  \n" +
			 "<when test='status != null'>\n" +
			 "and status = #{status}  \n" +
			 "</when>\n" +
			 "and is_delete = 0\n" +
			 "order by sort </script>"})
	 List<TestRecite> getTestReciteList(CourseIdParam courseIdParam);
	 
	 @Select("select * from test_recite where courseid=#{courseId} and status=0 and is_delete=0 order by sort ")
	 List<TestRecite> getTestReciteListId(Integer courseId);
	
	
}
