package com.quxueyuan.server.swagger.api;

import com.quxueyuan.bean.vo.req.CourseIdParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.common.util.StrCompareUtil;
import com.quxueyuan.common.util.tencent.objectStorageUtils.DownloadFileUtil;
import com.quxueyuan.common.util.tencent.objectStorageUtils.UploadFileUtil;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.TestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// 导入对应产品模块的client
// 导入要请求接口对应的request response类


/**
 *  测试题相关的接口入口 
 *  1：测试选择题列表 
 *  2：背诵选择题列表 
 *  3：错题选择题列表
 * @author majianchun
 *
 */
@Slf4j
@RestController
public class TestController {
	
	@Resource
	private TestService testService;
	@Resource
	private UploadFileUtil uploadFileUtil;
	@Resource
	private DownloadFileUtil downloadFileUtil;


	/**
	 * 习题列表
	 * @param courseIdParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "练习题列表", notes = "练习题列表")
	@PostMapping(value = "/test/gettestlist")
    public ResponseEntity getTestList(@RequestBody CourseIdParam courseIdParam) {
		try {
			log.info("getTestList - Param,{};", courseIdParam.toString());
			return testService.getTestList(courseIdParam);
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
	 * 练习题列表
	 * @param courseIdParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "练习题列表", notes = "练习题列表")
	@PostMapping(value = "/test/getchoiselist")
    public ResponseEntity getChoiseList(@RequestBody CourseIdParam courseIdParam) {
		try {
			log.info("getChoiseList - Param,{};", courseIdParam.toString());
			return testService.getChoiseList(courseIdParam);
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
	 * 朗诵题列表
	 * @param courseIdParam
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "朗诵题列表", notes = "朗诵题列表")
	@PostMapping(value = "/test/getrecitelist")
    public ResponseEntity getReciteList(@RequestBody CourseIdParam courseIdParam) {
		try {
			log.info("getReciteList - Param,{};", courseIdParam.toString());
			return testService.getReciteList(courseIdParam);
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
	 * 语音识别
	 */
	@ResponseBody
	@ApiOperation(value = "语音识别", notes = "语音识别")
	@PostMapping(value = "/test/voice2words")
	public ResponseEntity voice2words() {
		try {

			String filePath = "/Users/liuwei/Desktop/test.wav";
			String content = testService.voice2Words(filePath);

			return TouchApiResponse.success(content);
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
	 * 语音识别Demo
	 */
	@ResponseBody
	@ApiOperation(value = "语音识别Demo", notes = "语音识别Demo")
	@PostMapping(value = "/test/video2textdemo")
	public void video2textdemo() {
//		try{
//
//			//用户需修改为自己的secretid，secret_key,appid
//			String secret_key = "1rV1LTP9mE6qTu9CAznW4QaYKvLbgZLx";
//			String secretid = "AKIDCIDxVsI3sX1JUUEwOfk286sTs5A4uJlm";
//			String appid = "1257918120";

			// 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
//			Credential cred = new Credential(secretid, secret_key);
//
//			// 实例化要请求产品(以cvm为例)的client对象
//			CvmClient client = new CvmClient(cred, "ap-guangzhou");
//
//			// 实例化一个请求对象
//			DescribeZonesRequest req = new DescribeZonesRequest();
//
//			// 通过client对象调用想要访问的接口，需要传入请求对象
//			DescribeZonesResponse resp = client.DescribeZones(req);
//
//			// 输出json格式的字符串回包
//			System.out.println(DescribeZonesRequest.toJsonString(resp));
//		} catch (TencentCloudSDKException e) {
//			System.out.println(e.toString());
//		}
	}

	/**
	 * 计算两个字符串度匹配度 计算出边际距离的分数
	 */
	@ResponseBody
	@ApiOperation(value = "计算两个字符串度匹配度", notes = "计算两个字符串度匹配度")
	@PostMapping(value = "/test/strcompare")
	public void strcompare(@ApiParam(value = "str1") @RequestParam(value = "str1", required = false) String str1,
						   @ApiParam(value = "str2") @RequestParam(value = "str2", required = false) String str2) {
		try{

			testService.compareScore(str1, str2);
			System.out.println("similarityRatio=" + StrCompareUtil.getSimilarityRatio(str1, str2));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}


	@ResponseBody
	@PostMapping("/test/upload")
	public ResponseEntity getuploadTest(){
		boolean flag=uploadFileUtil.UploadFile("container1-1257948725","/Users/liruichen/Desktop/api.txt","upload_single_demo.txt1");
		if(flag==true){
			return TouchApiResponse.success();
		}
		return TouchApiResponse.failed();
	}

	@ResponseBody
	@PostMapping("/test/download")
	public ResponseEntity getdownloadTest(){
		boolean flag=downloadFileUtil.downloadFile("container1-1257948725","upload_single_demo.txt","/Users/liruichen/Desktop/apiiii");
		if(flag==true){
			return TouchApiResponse.success();
		}
		return TouchApiResponse.failed();
	}
}
