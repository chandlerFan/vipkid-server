package com.quxueyuan.server.api.service;

import com.quxueyuan.bean.vo.req.CourseIdParam;
import org.springframework.http.ResponseEntity;


public interface TestService {

	ResponseEntity getChoiseList(CourseIdParam courseIdParam);

	ResponseEntity getReciteList(CourseIdParam courseIdParam);

	ResponseEntity getTestList(CourseIdParam courseIdParam);

	int compareScore(String str1, String str2);

	String voice2Words(String filePath);
}
