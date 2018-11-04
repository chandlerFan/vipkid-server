package com.quxueyuan.server.service.impl.UserJoinServiceImpl;


import com.quxueyuan.bean.domain.CourseModule;
import com.quxueyuan.bean.domain.Sku;
import com.quxueyuan.bean.domain.UserJoin.User2Course;
import com.quxueyuan.bean.domain.UserJoin.User2TestChoise;
import com.quxueyuan.bean.domain.UserJoin.User2TestRecite;
import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.req.User2TestCommitParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.common.enmu.CourseStatusCode;
import com.quxueyuan.common.enmu.DeleteCode;
import com.quxueyuan.common.enmu.TopicTypeCode;
import com.quxueyuan.common.enmu.TouchApiCode;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.CourseModuleService;
import com.quxueyuan.server.api.service.CourseService;
import com.quxueyuan.server.api.service.User2TestChoiseService;
import com.quxueyuan.server.api.service.User2TestReciteService;
import com.quxueyuan.server.api.service.UserJoinService.User2CommitChoiseServer;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseModuleService;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseService;
import com.quxueyuan.server.dao.SkuMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class User2CommitChoiseServerImpl implements User2CommitChoiseServer {

	@Resource
	private CourseService courseService;

	@Resource
	private User2TestReciteService user2TestReciteService;

	@Resource
	private User2TestChoiseService user2TestChoiseService;

	@Resource
	private User2CourseService user2CourseService;

	@Resource
	private User2CourseModuleService user2CourseModuleService;

	@Resource
	private CourseModuleService courseModuleService;

	@Resource
	private SkuMapper skuMapper;

    @Override
    public ResponseEntity userChoiseQuestion(User2TestCommitParam user2CommitChoiseParam) throws Exception {

		//1 根据类型批量插入user2test_choise/user2test_recite 数据
		//2、判断课程类型
		//1、课程类型 是 周测/结果测试  只更新课程状态
		//2、课程类型 是 单课，则更新课程状态， 并且更新模块状态 因为测试题是最后一个模块对内容

		//取出sku中测试题的分数值
		Sku sku = skuMapper.slectSkuById(user2CommitChoiseParam.getSkuId());


		List<User2TestCommitParam.TopicAnswer> testTopicList = user2CommitChoiseParam.getTestTopicList();
		if (testTopicList == null || testTopicList.size() == 0) {
			throw new TouchCodeException(TouchApiCode.TOPIC_ANSWER_IS_NULL);
		}
		//选择题答案列表
		List<User2TestChoise> choiseList = new ArrayList<User2TestChoise>();
		//朗诵题答案列表
		List<User2TestRecite> reciteList = new ArrayList<User2TestRecite>();

		User2TestChoise user2TestChoise = null;
		User2TestRecite user2TestRecite = null;
		for (User2TestCommitParam.TopicAnswer topicAnswer : testTopicList) {
			if (topicAnswer.getType() == TopicTypeCode.TOPIC_TYPE_1.getCode()) {
				user2TestChoise = new User2TestChoise();
				TransferUtil.transferIgnoreNull(topicAnswer, user2TestChoise);
				user2TestChoise.setUserid(user2CommitChoiseParam.getUid());
				user2TestChoise.setTestChoiseId(topicAnswer.getTestTopicId());
				user2TestChoise.setCourseId(user2CommitChoiseParam.getCourseId());
				user2TestChoise.setIsDelete(DeleteCode.IS_DELETE_0.getCode());
				user2TestChoise.setTestScore(0);
				//分数赋值
				if(topicAnswer.getStatus() == 1){
					user2TestChoise.setTestScore(sku.getChoiseScore());
				}
				choiseList.add(user2TestChoise);
			} else if (topicAnswer.getType() == TopicTypeCode.TOPIC_TYPE_2.getCode()) {
				user2TestRecite = new User2TestRecite();
				TransferUtil.transferIgnoreNull(topicAnswer, user2TestRecite);
				user2TestRecite.setUserid(user2CommitChoiseParam.getUid());
				user2TestRecite.setTestReciteId(topicAnswer.getTestTopicId());
				user2TestRecite.setCourseId(user2CommitChoiseParam.getCourseId());
				user2TestRecite.setIsDelete(DeleteCode.IS_DELETE_0.getCode());
				//分数赋值
				user2TestRecite.setTestScore(0);
				//分数赋值
				if(topicAnswer.getStatus() == 1){
					user2TestRecite.setTestScore(sku.getReciteScore());
				}
				reciteList.add(user2TestRecite);
			}
		}
		user2TestReciteService.saveTestReciteBatch(reciteList);
		user2TestChoiseService.saveTestChoiseBatch(choiseList);

		//1、每道题分数相加（选择&填空）
		//1.1、选择题
		int choiseScore=0;
		for(int i=0;i<choiseList.size();i++){
			choiseScore=choiseList.get(i).getTestScore();
			++choiseScore;
		}
		int reciteScore=0;
		for(int i=0;i<reciteList.size();i++){
			reciteScore=reciteList.get(i).getTestScore();
			++reciteScore;
		}
		//2、总分
		int scoreAll=choiseScore+reciteScore;
		//3、最终更新数据 选择分、填空分、总分、数据库status 状态为3
		User2Course user2Course=new User2Course();
		user2Course.setUserId(user2CommitChoiseParam.getUid());
		user2Course.setCourseId(user2CommitChoiseParam.getCourseId());
		user2Course.setTrainingcampid(user2CommitChoiseParam.getTrainingcampId());
		user2Course.setScoreChoise(choiseScore);
		user2Course.setScoreRecite(reciteScore);
		user2Course.setScoreAll(scoreAll);
		user2Course.setStatus(CourseStatusCode.COURSE_STATUS_3.getCode());
		//获取星星等级
		GeneralParam generalParam=new GeneralParam();
		generalParam.setCourseId(user2CommitChoiseParam.getCourseId());
		generalParam.setUid(user2CommitChoiseParam.getUid());
		generalParam.setTrainingCampId(user2CommitChoiseParam.getTrainingcampId());
		courseService.updateStarNum(generalParam);
		int result=user2CourseService.updateUser2Course(user2Course);
		if(result<0){
			return TouchApiResponse.failed("更新用户参与课程信息失败");
		}
		//第四个模块更新状态
		List<CourseModule> courseModuleList1=courseModuleService.getCourseModule(user2CommitChoiseParam.getSkuId());
		Integer lastCourseModuleId=courseModuleList1.get(courseModuleList1.size()-1).getId();
		int updateresult=user2CourseModuleService.updateUser2CourseModuleStatus(user2CommitChoiseParam.getUid(),user2CommitChoiseParam.getCourseId(),lastCourseModuleId,user2CommitChoiseParam.getSkuId());
		if(updateresult>0) {
			return TouchApiResponse.success();
		}else{
			return TouchApiResponse.failed("更新模块状态失败");
		}

	}
}
