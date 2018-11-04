package com.quxueyuan.server.swagger.api.signup;

import com.quxueyuan.bean.domain.signup.Operate;
import com.quxueyuan.bean.vo.req.OperateParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.signup.OperateService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @Company: Vipkid
 * @Author Liuwei
 * @Date 2018年11月02日 11:54
 * @Description: 运营功能表
 */


/**
 * 运营功能表
 */
@Slf4j
@RestController
@RequestMapping("/operate")
public class OperateController {

	@Resource
	private OperateService operateService;

	/**
	 * 查询训运营功能表获取当期训练营报名二维码
	 *
	 * @param operateParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "查询训运营功能表获取当期训练营报名二维码", notes = "查询训运营功能表获取当期训练营报名二维码")
	@PostMapping(value = "/signupqrcodelist")
	public ResponseEntity signupqrcodelist(@RequestBody OperateParam operateParam) {
		try {
			log.info("signupqrcodelist - Param,{};", operateParam.toString());
			Operate operate = operateService.signupqrcodelist(operateParam);
			return TouchApiResponse.success(operate);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询训运营功能表获取当期训练营报名二维码:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

}
