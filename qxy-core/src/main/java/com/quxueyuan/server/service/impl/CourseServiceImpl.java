package com.quxueyuan.server.service.impl;

import com.google.common.collect.Maps;

import com.quxueyuan.bean.domain.Course;
import com.quxueyuan.bean.domain.SubLevel;
import com.quxueyuan.bean.domain.UserJoin.User2TrainingCamp;
import com.quxueyuan.bean.vo.req.CourseParam;
import com.quxueyuan.bean.vo.res.User2CourseListWrapper;
import com.quxueyuan.common.enmu.CourseStatusCode;
import com.quxueyuan.common.enmu.CourseTypeCode;
import com.quxueyuan.common.enmu.LevelStatusCode;
import com.quxueyuan.common.enmu.TouchApiCode;
import com.quxueyuan.common.util.date.DateUtil;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.CourseService;
import com.quxueyuan.server.dao.CourseMapper;
import com.quxueyuan.server.dao.SubLevelMapper;
import com.quxueyuan.server.dao.User2CourseMapper;
import com.quxueyuan.server.dao.User2TrainingCampMapper;
import com.quxueyuan.bean.domain.UserJoin.User2Course;
import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.res.*;
import com.quxueyuan.common.util.TransferUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;

	@Resource
	private SubLevelMapper subLevelMapper;

	@Resource
	private User2CourseMapper user2CourseMapper;

	@Resource
	private User2TrainingCampMapper user2TrainingCampMapper;

	 @Override
	 public Course getCourse(Integer trainingCampId, Integer levelId, Integer subLevelId, Integer type, Integer skuId){
		Course course=courseMapper.selectCouseByType(trainingCampId,levelId,subLevelId,type,skuId);
		return course;
	 }

	/**
	 * 古诗训练营课程首页
	 * @param courseParam  uid、trainingCampId、levelId、skuId
	 * 1、趣成长古诗训练营 title、用户姓名、期数、总分数
	 * 2、sublevel列表
	 * 3、当前sublevel的课程、用户参与的课程解锁状态、星星数
	 * @return
	 */
	@Override
	public ResponseEntity getCourseList(CourseParam courseParam) throws ParseException {
		Integer currentSubLevelId = courseParam.getSubLevelId(); // 参数中传递的需要查询的subLevelId
		User2CourseListWrapper user2CourseListWrapper = new User2CourseListWrapper();
		//总分数  暂不做处理
		//子Levellist
		List<SubLevel> subLevelList = subLevelMapper.seleteSubLevelByLevelId(courseParam.getLevelId());
		//用户参与的训练营
		User2TrainingCamp user2TrainingCamp = user2TrainingCampMapper.findUser2Training(courseParam.getUid(), courseParam.getTrainingCampId(), courseParam.getLevelId());
		//如果User2TrainingCamp中subLevel_id为空，则更新成第一个subLevel
		if(user2TrainingCamp == null){
			throw new TouchCodeException(TouchApiCode.USER_TRAININGCAMP_IS_NULL);
		}
		if(user2TrainingCamp.getSublevelId() == null){
			user2TrainingCamp.setSublevelId(subLevelList.get(0).getId());
			user2TrainingCamp.setSublevelName(subLevelList.get(0).getName());
			user2TrainingCampMapper.updateSubLevel(user2TrainingCamp);
		}
		user2CourseListWrapper.setFactSubLevelId(user2TrainingCamp.getSublevelId());
		//筛选当前subLevelId 的序号 i
		int i = 0;
		for(SubLevel subLevel : subLevelList){
			i++;
			if(subLevel.getId() == user2TrainingCamp.getSublevelId()){
				break;
			}
		}

		//获取下一SubLevelId   nextSubLevelId
		if(i < subLevelList.size()){
			user2CourseListWrapper.setNextSubLevelId(subLevelList.get(i).getId()); //有下一级别时传下一级别的subLevelId
		} else {
			user2CourseListWrapper.setNextSubLevelId(-1);  //没有下一级别时传-1
		}

		List<SubLevelVO> subLevelVOList = new ArrayList<SubLevelVO>();
		int j = 0;//如果j < i,则此subLevel已解锁
		SubLevelVO subLevelVO = null;
		for(SubLevel subLevel : subLevelList){
			int status = LevelStatusCode.LOCK_CLOSE.getCode();
			if(j < i){
				status = LevelStatusCode.LOCK_OPEN.getCode();
			}
			subLevelVO = new SubLevelVO();
			subLevelVO.setSubLevelId(subLevel.getId());
			subLevelVO.setName(subLevel.getName());
			subLevelVO.setStatus(status);
			//设置是否可解锁状态 和 可解锁日期
			setLockAndDate(subLevelVO, courseParam, subLevelList, j, user2TrainingCamp.getSublevelId());
			subLevelVOList.add(subLevelVO);
			j++;
		}

		if(currentSubLevelId == null){
			courseParam.setSubLevelId(user2TrainingCamp.getSublevelId());
			currentSubLevelId=user2TrainingCamp.getSublevelId();
		}
		//查询参数传递的subLevelId对应的课程
		List<User2CourseVo> user2CourseVoList = new LinkedList<User2CourseVo>();
		List<Course> courseList = courseMapper.getSublevelCourseList(courseParam);
		//用户参与的课程解锁状态、星星数
		User2CourseVo user2CourseVo = null;
		for(Course course : courseList){
			//查询课程解锁状态、星星数量、单课总分数
			User2Course user2Course = user2CourseMapper.selectUser2CourseByUserIdAndCourseId(courseParam.getUid(), course.getId(), courseParam.getTrainingCampId());
			//计算解锁状态
			user2Course = calculationCourseStatus(course, user2Course, courseParam);
			user2CourseVo = packingUser2CourseVO(course, user2Course);
			user2CourseVoList.add(user2CourseVo);
		}

		//封装levelJump
//		User2CourseListWrapper.LevelJump levelJump = packingLevelJump(subLevelList, courseParam, user2TrainingCamp.getSublevelId());

		user2CourseListWrapper.setSubLevelVOList(subLevelVOList);
		user2CourseListWrapper.setSubLevelId(currentSubLevelId);
		user2CourseListWrapper.setUser2CourseVoList(user2CourseVoList);
//		user2CourseListWrapper.setLevelJump(levelJump);
		return TouchApiResponse.success(user2CourseListWrapper);
	}

	/**
	 * 设置是否可解锁状态 和 可解锁日期
	 * @param subLevelVO
	 * @param courseParam
	 */
	private void setLockAndDate(SubLevelVO subLevelVO, CourseParam courseParam, List<SubLevel> subLevelList, int j, int currentSubLevelId) {
		//判断是否可解锁状态逻辑，Level 3可升级日期 晚于 Level 2结课时间 ； Level 2可升级日期 晚于 Level 1结课时间
		if(j == 0){
			return;
		}
		//获取上一subLevel的结课日期
		SubLevel subLevel = subLevelList.get(j - 1);
		CourseParam temp = new CourseParam();
		TransferUtil.transferIgnoreNull(courseParam, temp);
		temp.setSubLevelId(subLevel.getId());
		List<Course> courseList = courseMapper.getSublevelCourseList(temp);
		Course endCourse = courseList.get(0);
		subLevelVO.setDate(DateUtil.addDate(endCourse.getStudyDate(), 1));//可解锁日期
		int val = DateUtil.compareDate(DateUtil.getDate(), subLevelVO.getDate());
		if(val == 0 || val == 1){//当前日期大于等于可解锁日期，则设置为可解锁状态
			subLevelVO.setStatus(2);
		}
		if(currentSubLevelId == subLevelVO.getSubLevelId()){//如果是当前的level则status为0 解锁状态
			subLevelVO.setStatus(0);
		}
	}

	/**
	 * 封装levelJump
	 * @return
	 */
//	private User2CourseListWrapper.LevelJump packingLevelJump(List<SubLevel> subLevelList, CourseParam courseParam, int currentLevelId) {
//		User2CourseListWrapper.LevelJump levelJump = new User2CourseListWrapper.LevelJump();
//		//当前subLevelId
//		int levelCount = subLevelList.size();
//		SubLevel currentSubLevel = null;
//		SubLevel nextSubLevel = null;
//		int i = 0;
//		for(SubLevel subLevel : subLevelList){
//			i++;
//			if(subLevel.getId() == currentLevelId){
//				currentSubLevel = subLevel;
//				break;
//			}
//		}
//
//		levelJump.setCurrentSubLevelId(currentSubLevel.getId());
//		levelJump.setCurrentSubLevelName(currentSubLevel.getName());
//		//当前subLevel开始、结束时间
//		courseParam.setSubLevelId(currentLevelId);
//		setLevelTime(levelJump, courseParam, "current");
//
//		if(i < levelCount){// 如果相等说明此 当前subLevel是最后一个level
//			nextSubLevel = subLevelList.get(i);
//			levelJump.setNextSubLevelId(nextSubLevel.getId());
//			levelJump.setName(nextSubLevel.getName());
//			//下一subLevel开始、结束时间
//			courseParam.setSubLevelId(nextSubLevel.getId());
//			setLevelTime(levelJump, courseParam, "next");
//		}
//		int val = DateUtil.compareDate(DateUtil.getDate(), DateUtil.parseDate(DateUtil.formatDate(levelJump.getCurrentSubLevelEndDate())));
//		if(val == 1 || val == 0){//当前时间大于当前subLevel的结束日期则可以跳转
//			levelJump.setStatus(0); //0可跳转，1不可跳转
//		}else{
//			levelJump.setStatus(1); //0可跳转，1不可跳转
//		}
//		return levelJump;
//	}

	/**
	 * 设置sublevel的课程开始、结束时间
	 * @param levelJump
	 * @param courseParam
	 */
//	private void setLevelTime(User2CourseListWrapper.LevelJump levelJump, CourseParam courseParam, String flag) {
//		//查询此subLevel下的所有课程  课程倒序排列
//		List<Course> courseList = courseMapper.getSublevelCourseList(courseParam);
//		if(courseList.size() == 0){
//			throw new TouchCodeException(TouchApiCode.SUBLEVEL_COURSE_IS_NULL);
//		}
//		if(courseList != null && courseList.size() > 0) {
//			Course startCourse = courseList.get(courseList.size() - 1);
//			Course endCourse = courseList.get(0);
//
//			if (flag.equals("current")) {
//				levelJump.setCurrentSubLevelStartDate(startCourse.getStudyDate());
//				levelJump.setCurrentSubLevelEndDate(endCourse.getStudyDate());
//			} else if (flag.equals("next")) {
//				levelJump.setStudyDate(startCourse.getStudyDate());
//				levelJump.setNextSubLevelEndDate(endCourse.getStudyDate());
//			}
//		}
//	}

	/**
	 * 封装 User2CourseVO
	 * @param course
	 * @return
	 */
	public User2CourseVo packingUser2CourseVO(Course course, User2Course user2Course) {
		int status = 1; //0解锁、1、不能解锁、2可解锁、3完成
		int scoreAll = 0;
		int starLevel = 0;
		if(user2Course != null){
			status = user2Course.getStatus();
			scoreAll = user2Course.getScoreAll();
			starLevel = user2Course.getStarLevel();
		}
		User2CourseVo user2CourseVo = new User2CourseVo();
		user2CourseVo.setCourseId(course.getId());
		user2CourseVo.setName(course.getName());
		user2CourseVo.setStatus(status);
		user2CourseVo.setSort(course.getSort());
		user2CourseVo.setTrainingCampId(course.getTrainingCampId());
		user2CourseVo.setSkuId(course.getSkuId());
		user2CourseVo.setLevelId(course.getLevelId());
		user2CourseVo.setSubLevelId(course.getSubLevelId());
		user2CourseVo.setSubLevelName(course.getSubLevelName());
		user2CourseVo.setStudyDate(course.getStudyDate());
		user2CourseVo.setType(course.getType());
		user2CourseVo.setScore(scoreAll);
		user2CourseVo.setStarLevel(starLevel);
		user2CourseVo.setLockPic(course.getLockPic());
		user2CourseVo.setUnlockPic(course.getUnlockPic());
		return user2CourseVo;
	}

	/**
	 * 计算周测和课程解锁状态
	 * @param course, user2Course ,courseParam
	 */
	private User2Course calculationCourseStatus(Course course, User2Course user2Course, CourseParam courseParam) throws ParseException {

		if(user2Course == null){
			//类型为课程
			if(course.getType() == CourseTypeCode.COURSE_TYPE_1.getCode()){
				//当前日期 >= 课程的开课日期
				//课程日期
				int val = DateUtil.getDate().compareTo(DateUtil.parseDate(DateUtil.formatDate(course.getStudyDate())));
				if(val == 1){
					user2Course = packingUser2Course(user2Course, course.getId());
					//2018-10-24 协商定 如果当前日期定课程则是 2 可解锁状态
					user2Course.setStatus(CourseStatusCode.COURSE_STATUS_2.getCode());
				}
				if(val == 0){
					user2Course = packingUser2Course(user2Course, course.getId());
				}
			} else
				//类型为周测  逻辑：根据本周课程的状态判断
				if(course.getType() == CourseTypeCode.COURSE_TYPE_2.getCode()){
					//判断本周的课程是否都已完成，根据周测课程的id获取用户参与本周的课程list
					List<Map<String, Object>> list = user2CourseMapper.selectUser2CourseByWeek(courseParam.getTrainingCampId(), courseParam.getSkuId(), courseParam.getLevelId(), courseParam.getSubLevelId(), course.getId());
					int len = list.size();
					for(Map<String, Object> map : list){
						len--;
						if(map.get("status") == null || Integer.parseInt(map.get("status").toString()) != CourseStatusCode.COURSE_STATUS_3.getCode()){
							break;
						}
					}
					if(len == 0){//课程都学完，可以周测
						user2Course = packingUser2Course(user2Course, course.getId());
					}
				}else //类型为结课的解锁逻辑  逻辑：根据第三周的周测结果判断 倒序排列取第一条
					if(course.getType() == CourseTypeCode.COURSE_TYPE_3.getCode()){
						List<Map<String, Object>> list = user2CourseMapper.selectUser2CourseByWeek(courseParam.getTrainingCampId(), courseParam.getSkuId(), courseParam.getLevelId(), courseParam.getSubLevelId(), course.getId());
						Map<String, Object> map = list.get(0);
						if(map.get("status") != null && Integer.parseInt(map.get("status").toString()) == CourseStatusCode.COURSE_STATUS_3.getCode()){
							user2Course = packingUser2Course(user2Course, course.getId());
						}
					}else //类型为奖状的解锁逻辑  逻辑：根据结课考试的结果判断
						if(course.getType() == CourseTypeCode.COURSE_TYPE_4.getCode()){
							List<Map<String, Object>> list = user2CourseMapper.selectUser2CourseByWeek(courseParam.getTrainingCampId(), courseParam.getSkuId(), courseParam.getLevelId(), courseParam.getSubLevelId(), course.getId());
							Map<String, Object> map = list.get(0);
							if(map.get("status") != null && Integer.parseInt(map.get("status").toString()) == CourseStatusCode.COURSE_STATUS_3.getCode()){
								user2Course = packingUser2Course(user2Course, course.getId());
							}
						}
		}
		return user2Course;
	}

	/**
	 * 封装User2Course对象
	 * @param courseId
	 */
	private User2Course packingUser2Course(User2Course user2Course, Integer courseId) {
		user2Course = new User2Course();
		user2Course.setCourseId(courseId);
		user2Course.setStarLevel(0);
		user2Course.setScoreAll(0);
		user2Course.setStatus(CourseStatusCode.COURSE_STATUS_0.getCode());
		return user2Course;
	}

	/**
	 * 课程根据分数设置星星数量
	 * @param generalParam
	 * @return
	 */
	@Override
	public ResponseEntity updateStarNum(GeneralParam generalParam) {
		User2Course user2Course = user2CourseMapper.selectUser2CourseByUserIdAndCourseId(generalParam.getUid(), generalParam.getCourseId(), generalParam.getTrainingCampId());
		int scoreAll = user2Course.getScoreAll();
		int starLevel = 0;
		if(scoreAll >= 0 && scoreAll <= 40){
			starLevel = 0;
		} else if(scoreAll > 40 && scoreAll <= 70){
			starLevel = 1;
		} else if(scoreAll > 70 && scoreAll <= 90){
			starLevel = 2;
		} else if(scoreAll > 90 && scoreAll <= 100){
			starLevel = 3;
		}
		user2Course.setStarLevel(starLevel);
		user2CourseMapper.updateCourseStarLevel(user2Course);
		return TouchApiResponse.success();
	}

	/**
	 * 升级SubLevel
	 * @param courseParam
	 * @return
	 */
	@Override
	public ResponseEntity upSubLevel(CourseParam courseParam) {
		Map map = Maps.newHashMap();
		//升级到的subLevel
		SubLevel toSubLevel = subLevelMapper.findById(courseParam.getSubLevelId());
		//需要验证是否可以升级
		List<Course> courseList = courseMapper.getSublevelCourseList(courseParam);
		Course endCourse = courseList.get(0);
		Date unlockDate = DateUtil.addDate(endCourse.getStudyDate(), 1);//可解锁日期
		int val = DateUtil.compareDate(DateUtil.getDate(), unlockDate);
		if(val == -1){//当前日期大于等于可解锁日期，则设置为可解锁状态    等于-1时说明没到解锁日期
			map.put("data", TouchApiCode.NO_DATE_TO_UP.getMsg());
			map.put("msg", "在"+ DateUtil.formatDate(unlockDate) +"日可以升级到"+toSubLevel.getName()+"课程，敬请期待");
			map.put("code", 1);//0成功， 1失败
			return TouchApiResponse.success(map);
		}

		//1、更改当前subLevel标识  user2trainingcamp
		User2TrainingCamp user2trainingcamp = user2TrainingCampMapper.findUser2Training(courseParam.getUid(), courseParam.getTrainingCampId(), courseParam.getLevelId());
		user2trainingcamp.setSublevelId(toSubLevel.getId());
		user2trainingcamp.setSublevelName(toSubLevel.getName());
		user2TrainingCampMapper.updateSubLevel(user2trainingcamp);
		map.put("code", 0);//0成功， 1失败
		return TouchApiResponse.success(map);
	}
}
