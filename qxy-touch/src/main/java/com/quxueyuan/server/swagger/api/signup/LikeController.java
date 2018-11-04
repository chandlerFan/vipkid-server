package com.quxueyuan.server.swagger.api.signup;

import com.quxueyuan.bean.vo.req.LikeParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.signup.LikeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/like")
public class LikeController {

	@Resource
	private LikeService likeService;

	/**
	 * 点赞
	 *
	 * @param likeParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "点赞", notes = "点赞")
	@PostMapping(value = "/like")
	public ResponseEntity like(@RequestBody LikeParam likeParam) {
		try {
			log.info("like - Param,{};", likeParam.toString());
			int result=likeService.like(likeParam);
			if(result==1) {
				return TouchApiResponse.success();
			}
			return TouchApiResponse.failed("点赞失败");
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
