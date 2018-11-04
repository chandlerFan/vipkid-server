package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.TestChoise;
import com.quxueyuan.bean.domain.TestRecite;
import com.quxueyuan.bean.vo.req.CourseIdParam;
import com.quxueyuan.bean.vo.res.TestChoiseVO;
import com.quxueyuan.bean.vo.res.TestListVO;
import com.quxueyuan.bean.vo.res.TestReciteVO;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.common.util.StrCompareUtil;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.common.util.tencent.Voice2WordsUtil;
import com.quxueyuan.server.api.service.TestService;
import com.quxueyuan.server.dao.TestMapper;
import com.quxueyuan.server.dao.User2TestChoiseMapper;
import com.quxueyuan.server.dao.User2TestReciteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
	
	@Resource
	private TestMapper testMapper;
	@Resource
	private User2TestChoiseMapper user2TestChoiseMapper;
	@Resource
	private User2TestReciteMapper user2TestReciteMapper;
	@Resource
	private Voice2WordsUtil voice2WordsUtil;

	/**
	 * 选择题列表
	 * @param courseIdParam
	 * @return
	 */
	@Override
	public ResponseEntity getChoiseList(CourseIdParam courseIdParam) {
		//错题列表 前端传入的是status = 0 即为查询错题
		List<TestReciteVO> testReciteVOList =findTestReciteVO(courseIdParam);
		List<TestChoiseVO> testchoiseVOList = findTestChoiseVO(courseIdParam);

		return TouchApiResponse.success(testchoiseVOList);
	}

	private List<TestChoiseVO> findTestChoiseVO(CourseIdParam courseIdParam){
		List<TestChoise> testChoiseList = testMapper.getTestChoiseList(courseIdParam);
		List<TestChoiseVO> testchoiseVOList = new LinkedList<TestChoiseVO>();
		for(TestChoise testChoise : testChoiseList) {
			TestChoiseVO testChoiseVO = new TestChoiseVO();
			TransferUtil.transferIgnoreNull(testChoise, testChoiseVO);
			testChoiseVO.setTestchoiseId(testChoise.getId());
			testchoiseVOList.add(testChoiseVO);
		}
		return testchoiseVOList;
	}

	/**
	 * 朗诵题列表
	 * @param courseIdParam
	 * @return
	 */
	@Override
	public ResponseEntity getReciteList(CourseIdParam courseIdParam) {
		List<TestReciteVO> testReciteVOList = findTestReciteVO(courseIdParam);
		return TouchApiResponse.success(testReciteVOList);
	}

	private List<TestReciteVO> findTestReciteVO(CourseIdParam courseIdParam){
		List<TestRecite> testReciteList = testMapper.getTestReciteList(courseIdParam);
		List<TestReciteVO> testReciteVOList = new LinkedList<TestReciteVO>();
		for(TestRecite testRecite :testReciteList) {
			TestReciteVO testReciteVO = new TestReciteVO();
			TransferUtil.transferIgnoreNull(testRecite, testReciteVO);
			testReciteVO.setTestreciteId(testRecite.getId());
			testReciteVOList.add(testReciteVO);
		}
		return testReciteVOList;
	}

	@Override
	public ResponseEntity getTestList(CourseIdParam courseIdParam) {
		TestListVO testListVO = new TestListVO();
		List<TestChoiseVO> testchoiseVOList;
		List<TestReciteVO> testReciteVOList;
		if(courseIdParam.getStatus() == null){
			//其它的全部的题
			// TODO Auto-generated method stub
			testReciteVOList = findTestReciteVO(courseIdParam);
			testListVO.setReciteList(testReciteVOList);
			testchoiseVOList = findTestChoiseVO(courseIdParam);
			testListVO.setChoiseList(testchoiseVOList);
			return TouchApiResponse.success(testListVO);
		} else {
			//错误的题(选择题)
			//1、user2testchoise查询错题 status=1 //2、关联testchoise表
			testchoiseVOList=user2TestChoiseMapper.selectUser2ErrorChoise(courseIdParam);
			testReciteVOList=user2TestReciteMapper.selectUser2ErrorRecite(courseIdParam);
			testListVO.setChoiseList(testchoiseVOList);
			testListVO.setReciteList(testReciteVOList);
			return TouchApiResponse.success(testListVO);
		}
	}

	/**
	 * 获取字符串对比后的分数值
	 * @param str1
	 * @param str2
	 */
	@Override
	public int compareScore(String str1, String str2) {
		return StrCompareUtil.getCompareVal(str1, str2);
	}

	/**
	 * 音频文件转文字  语音识别
	 * @param filePath
	 * @return
	 */
	@Override
	public String voice2Words(String filePath) {

		return voice2WordsUtil.voice2Words(filePath);

	}

}
