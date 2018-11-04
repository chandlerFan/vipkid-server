package com.quxueyuan.server.swagger.api;

import com.quxueyuan.bean.vo.req.CourseParam;
import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CourseController {


	@Resource
	private CourseService courseService;


	/**
	 * 获取 某个训练营 课程列表
	 *
	 * @param courseParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "古诗训练营课程首页", notes = "古诗训练营课程首页")
	@PostMapping(value = "/course/getcourselist")
	public ResponseEntity getcourselist(@RequestBody CourseParam courseParam) {
		try {
			log.info("getcourselist - Param,{};", courseParam.toString());
			return courseService.getCourseList(courseParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗训练营课程首页接口:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 升级subLevel
	 *
	 * @param courseParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "升级subLevel", notes = "升级subLevel")
	@PostMapping(value = "/course/upsublevel")
	public ResponseEntity upSubLevel(@RequestBody CourseParam courseParam) {
		try {
			log.info("upSubLevel - Param,{};", courseParam.toString());
			return courseService.upSubLevel(courseParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗训练营课程首页接口:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 课程根据分数设置星星数量
	 *
	 * @param generalParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "课程根据分数设置星星数量", notes = "课程根据分数设置星星数量")
	@PostMapping(value = "/course/updatestarnum")
	public ResponseEntity updateStarNum(@RequestBody GeneralParam generalParam) {
		try {
			log.info("updateStarNum - Param,{};", generalParam.toString());
			return courseService.updateStarNum(generalParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗训练营课程首页接口:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}





}
