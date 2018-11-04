package com.quxueyuan.server.api.service;


import com.quxueyuan.bean.vo.req.CourseIdParam;
import org.springframework.http.ResponseEntity;

public  interface PoemSkuService {

	ResponseEntity getSpellModule(CourseIdParam courseIdParam);

	ResponseEntity getFamouseTeacher(CourseIdParam courseIdParam);
	

}
