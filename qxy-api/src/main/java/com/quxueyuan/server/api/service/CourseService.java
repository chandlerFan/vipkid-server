package com.quxueyuan.server.api.service;

import com.quxueyuan.bean.domain.Course;
import com.quxueyuan.bean.vo.req.CourseParam;
import com.quxueyuan.bean.vo.req.GeneralParam;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface CourseService {

	ResponseEntity getCourseList(CourseParam courseParam) throws ParseException;

	Course getCourse(Integer trainingCampId, Integer levelId, Integer subLevelId, Integer type, Integer skuId);

	ResponseEntity updateStarNum(GeneralParam generalParam);

	ResponseEntity upSubLevel(CourseParam courseParam);
}
