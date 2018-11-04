package com.quxueyuan.server.swagger.api.signup;


import com.quxueyuan.bean.vo.req.UserClassParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.signup.UserClassService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/activate")
public class ActivateController {

	@Resource
	private UserClassService userClassService;

	/**
	 * 激活
	 *
	 * @param userClassParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "激活", notes = "激活")
	@PostMapping(value = "/activate")
	public ResponseEntity activate(@RequestBody UserClassParam userClassParam) {
		try {
			log.info("activate - Param,{};", userClassParam.toString());
			userClassService.activate(userClassParam);
			return TouchApiResponse.success();
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
