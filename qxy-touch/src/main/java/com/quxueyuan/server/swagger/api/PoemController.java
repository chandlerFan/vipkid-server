package com.quxueyuan.server.swagger.api;

import com.quxueyuan.bean.vo.req.*;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.PoemSkuService;
import com.quxueyuan.server.api.service.PoemskuDetailService;
import com.quxueyuan.server.api.service.User2ReciteService;
import com.quxueyuan.server.api.service.UserJoinService.User2CommitChoiseServer;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PoemController {
	
	
	  @Resource
      private PoemskuDetailService poemskuDetailService;
	  @Resource
	  private PoemSkuService poemSkuService;
	  @Resource
	  private User2CommitChoiseServer user2TestChoiseServer;
	  @Resource
	  private User2ReciteService user2ReciteService;
	  @Resource
	  private User2CourseService user2CourseService;

	/**
	 * 查询古诗sku-古诗朗诵页 模块3接口 - 分页demo
	 * @param generalParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "分页", notes = "分页")
	@RequestMapping(value = "/poem/page", method = RequestMethod.POST)
	public ResponseEntity page(@ApiParam(value = "页码") @RequestParam(value = "page", required = false) Integer page,
									  @ApiParam(value = "每页数量") @RequestParam(value = "pageSize", required = false) Integer pageSize,
									  @Validated @RequestBody GeneralParam generalParam) {
		PageReq<GeneralParam> pageReq = new PageReq<>(page, pageSize, generalParam);
		try {
			log.info("getuserinfo - Param,{};", generalParam.toString());
			return poemskuDetailService.getPageList(pageReq.getPage(), pageReq.getPageSize(), pageReq.getSearch());
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询古诗sku-古诗朗诵页:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}


	/**
	 * 查询古诗sku-古诗朗诵页 模块3接口
	 * @param generalParam
	 * @return
	 */
    @ResponseBody
	@ApiOperation(value = "查询古诗sku-古诗朗诵页", notes = "查询古诗sku-古诗朗诵页")
    @RequestMapping(value = "/poem/getrecite", method = RequestMethod.POST)
    public ResponseEntity getuserinfo(@Validated @RequestBody GeneralParam generalParam) {
		try {
			log.info("getuserinfo - Param,{};", generalParam.toString());
			return poemskuDetailService.getrecite(generalParam.getUid(), generalParam.getCourseId());
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询古诗sku-古诗朗诵页:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
    }

	/**
	 * 古诗sku-古诗朗诵页 提交录音 接口-APP
	 * @param commitrecitationParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "古诗sku-古诗朗诵页 提交录音-APP", notes = "古诗sku-古诗朗诵页 提交录音-APP")
	@RequestMapping(value = "/poem/commitrecitationApp", method = RequestMethod.POST)
	public ResponseEntity commitrecitationApp(@RequestBody CommitrecitationParam commitrecitationParam) {
		try {
			log.info("commitrecitationApp - Param,{};", commitrecitationParam.toString());
			return user2ReciteService.commitrecitationApp(commitrecitationParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗sku-古诗朗诵页 提交录音 接口-APP:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

    /**
     * 古诗sku-古诗朗诵页 提交录音 接口-WX
     * @param commitrecitationParam
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "古诗sku-古诗朗诵页 提交录音-WX", notes = "古诗sku-古诗朗诵页 提交录音-WX")
    @RequestMapping(value = "/poem/commitrecitationWx", method = RequestMethod.POST)
    public ResponseEntity commitrecitationWx(@RequestBody CommitrecitationParam commitrecitationParam) {
		try {
			log.info("commitrecitationWx - Param,{};", commitrecitationParam.toString());
			return user2ReciteService.commitrecitationWx(commitrecitationParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗sku-古诗朗诵页 提交录音 接口-WX:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
    }

	/**
	 * 古诗sku-单课背诵练习题,周测，结课测试 背诵录音提交 接口-APP-WX
	 * @param commitReciteParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "古诗sku-背诵练习题 背诵录音提交-WX", notes = "古诗sku-背诵练习题 背诵录音提交-WX")
	@RequestMapping(value = "/poem/commitreciteWx", method = RequestMethod.POST)
	public ResponseEntity commitreciteWx(@RequestBody CommitReciteParam commitReciteParam) {
		try {
			log.info("commitreciteWx - Param,{};", commitReciteParam.toString());
			return user2ReciteService.commitreciteWx(commitReciteParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗sku-古诗朗诵页 提交录音 接口-WX:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

    /**
     * 古诗sku-单课背诵练习题,周测，结课测试 背诵录音提交 接口-APP
     * @param commitrecitationParam
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "古诗sku-背诵练习题 背诵录音提交-APP", notes = "古诗sku-背诵练习题 背诵录音提交-APP")
    @RequestMapping(value = "/poem/commitreciteApp", method = RequestMethod.POST)
    public ResponseEntity commitreciteApp(@RequestBody CommitrecitationParam commitrecitationParam) {

        try{
        	log.info("commitrecitationApp - Param,{};", commitrecitationParam.toString());
			return user2ReciteService.commitreciteApp(commitrecitationParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗sku-背诵练习题 背诵录音提交 接口-APP:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
    }

	/**
	 * 查询古诗sku-古诗朗诵页 模块3接口
	 * @param auditionrecitationParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "古诗sku-古诗朗诵页获取试听录音", notes = "古诗sku-古诗朗诵页获取试听录音")
	@RequestMapping(value = "/poem/auditionrecitation", method = RequestMethod.POST)
	public ResponseEntity auditionrecitation(@RequestBody AuditionrecitationParam auditionrecitationParam) {
		try{
			log.info("auditionrecitation - Param,{};", auditionrecitationParam.toString());
			return user2ReciteService.auditionrecitation(auditionrecitationParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗sku-古诗朗诵页获取试听录音接口:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 查询 古诗sku-朗诵测试报告 接口
	 * @param generalParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "朗诵测试报告", notes = "朗诵测试报告")
	@RequestMapping(value = "/poem/getrecitereport", method = RequestMethod.POST)
	public ResponseEntity getReciteReport(@RequestBody GeneralParam generalParam) {
		try{
			log.info("gettestresultreport - Param,{};", generalParam.toString());
			return user2CourseService.getReciteReport(generalParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗sku-周测和结果测试报告接口:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 查询 古诗sku-周测测试报告 接口
	 * @param generalParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "周测测试报告", notes = "周测测试报告")
	@RequestMapping(value = "/poem/getweekreport", method = RequestMethod.POST)
	public ResponseEntity getWeekReport(@RequestBody GeneralParam generalParam) {
		try{
			log.info("gettestresultreport - Param,{};", generalParam.toString());
			return user2CourseService.getWeekReport(generalParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("古诗sku-周测和结果测试报告接口:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 查询 古诗sku-拼音讲解页- 模块1接口
	 * @param courseIdParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "查询古诗sku-拼音讲解页", notes = "查询古诗sku-拼音讲解页")
    @PostMapping(value = "/poem/getspell")
    public ResponseEntity getSpell(@RequestBody CourseIdParam courseIdParam) {
		try{
			log.info("getSpell - Param,{};", courseIdParam.toString());
			return poemSkuService.getSpellModule(courseIdParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询古诗sku-拼音讲解页:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
        
    }

	/**
	 * 查询 古诗sku-名师讲古诗页- 模块2
	 * @param courseIdParam
	 * @return
	 */
    @ResponseBody
	@ApiOperation(value = "查询古诗sku-名师讲古诗页", notes = "查询古诗sku-名师讲古诗页")
    @PostMapping(value = "/poem/getfamousteacher")
    public ResponseEntity getFamouseTeacher(@RequestBody CourseIdParam courseIdParam) {
		try{
			log.info("getSpell - Param,{};", courseIdParam.toString());
			return poemSkuService.getFamouseTeacher(courseIdParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询古诗sku-拼音讲解页:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
    }

	/**
	 * 选择提答案提交
	 * @param user2CommitChoiseParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "选择提答案提交-批量", notes = "选择提答案提交-批量")
	@PostMapping("/poem/commitchoise")
	public ResponseEntity commitChoise(@RequestBody User2TestCommitParam user2CommitChoiseParam){
		try{
			log.info("commitChoise - Param,{};", user2CommitChoiseParam.toString());
			return user2TestChoiseServer.userChoiseQuestion(user2CommitChoiseParam);
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询古诗sku-拼音讲解页:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

	/**
	 * 朗诵题提交语音，返回分值
	 * @param user2CommitChoiseParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "朗诵题提交语音，返回分值", notes = "朗诵题提交语音，返回分值")
	@PostMapping("/poem/returnScore")
	public ResponseEntity returnScore(@RequestBody User2TestCommitParam user2CommitChoiseParam) {
		try {
			//待实现
			return null;
		} catch (TouchCodeException te) {
			log.error("请求返回异常:{}", te.getMessage());
			return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询古诗sku-拼音讲解页:{}", e.getMessage());
			return TouchApiResponse.failed(e.getMessage());
		}
	}

}
