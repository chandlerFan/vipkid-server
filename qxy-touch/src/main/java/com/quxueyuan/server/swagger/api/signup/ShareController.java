package com.quxueyuan.server.swagger.api.signup;

import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.req.ShareParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseService;
import com.quxueyuan.server.api.service.signup.ShareService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/share")
public class ShareController {

	@Resource
	private ShareService shareService;
	@Resource
	private User2CourseService user2CourseService;
	/**
	 * 分享
	 *
	 * @param shareParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "分享", notes = "分享")
	@PostMapping(value = "/share")
	public ResponseEntity share(@RequestBody ShareParam shareParam) {
		try {
			log.info("share - Param,{};", shareParam.toString());
			shareService.share(shareParam);
			return TouchApiResponse.success();
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分享:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 课程报告分享
	 *
	 * @param generalParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "课程报告分享", notes = "课程报告分享")
	@PostMapping(value = "/reportshare")
	public ResponseEntity reportshare(@RequestBody GeneralParam generalParam) {
		try {
			log.info("reportshare - Param,{};", generalParam.toString());
			return user2CourseService.getReciteReport(generalParam);
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
