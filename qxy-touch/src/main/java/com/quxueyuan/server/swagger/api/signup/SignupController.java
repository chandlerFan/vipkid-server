package com.quxueyuan.server.swagger.api.signup;

import com.quxueyuan.bean.vo.req.SignupParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.signup.SignupService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/signup")
public class SignupController {

	@Resource
	private SignupService signupService;

	/**
	 * 查询用户参与训练营表验证是否报名、是否激活，返回当前状态
	 *
	 * @param signupParam
	 * 1、存在数据为报名
	 * 2、status为0为没有激活，1为激活
	 * @param signupParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "检查报名状态", notes = "检查报名状态")
	@PostMapping(value = "/check")
	public ResponseEntity check(@RequestBody SignupParam signupParam) {
		try {
			log.info("check - Param,{};", signupParam.toString());

			Map<String, Object> map = signupService.check(signupParam);
			return TouchApiResponse.success(map);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("检查报名状态:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 报名
	 *
	 * 插入用户参与训练营表状态为报名成功，并且插入用户老师关系表 先查用户老师关系表，有就取，无就插入 每报名100人会更换一个老师的微信二维码图片
	 *
	 *
	 *
	 * @param signupParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "报名", notes = "报名")
	@PostMapping(value = "/signup")
	public ResponseEntity signup(@RequestBody SignupParam signupParam) {
		try {
			log.info("signup - Param,{};", signupParam.toString());
			return TouchApiResponse.success(signupService.signup(signupParam));
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
