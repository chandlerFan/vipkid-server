package com.quxueyuan.server.swagger.api.signup;

import com.quxueyuan.bean.vo.req.CourseParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {

	/**
	 * 模版  必须按照try——catch方式来实现
	 *
	 * @param courseParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "模版", notes = "模版")
	@PostMapping(value = "/getcourselist")
	public ResponseEntity getcourselist(@RequestBody CourseParam courseParam) {
		try {
			log.info("getcourselist - Param,{};", courseParam.toString());
			return null;
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
