package com.quxueyuan.server.service.impl.UserJoinServiceImpl;


import com.quxueyuan.bean.domain.*;
import com.quxueyuan.bean.domain.UserJoin.User2Course;
import com.quxueyuan.bean.domain.UserJoin.User2CourseModule;
import com.quxueyuan.bean.domain.UserJoin.User2TrainingCamp;
import com.quxueyuan.bean.vo.req.CourseModuleParam;
import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.req.User2CourseControllerParam;
import com.quxueyuan.bean.vo.res.*;
import com.quxueyuan.common.enmu.CourseModuleStatusCode;
import com.quxueyuan.common.enmu.CourseStatusCode;
import com.quxueyuan.common.enmu.DeleteCode;
import com.quxueyuan.common.util.StaticData;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.server.api.service.*;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseModuleService;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseService;
import com.quxueyuan.server.api.service.UserJoinService.User2JoinTrainingCampService;
import com.quxueyuan.server.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class User2CourseServiceImpl implements User2CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CourseModuleService courseModuleService;
    @Resource
    private User2CourseModuleService user2CourseModuleService;
    @Resource
    private User2CourseMapper user2CourseMapper;
    @Resource
    private TrainingCmapService trainingCmapService;
    @Resource
    private User2JoinTrainingCampService user2JoinTrainingCampService;
    @Resource
    private UserService userService;
    @Resource
    private SkuService skuService;
    @Resource
    private User2CourseModuleMapper user2CourseModuleMapper;
    @Resource
    private CourseService courseService;
    @Resource
    private PoemskuDetailMapper poemskuDetailMapper;
    @Resource
    private User2ReciteService user2ReciteService;
    @Resource
    private TestReciteMapper testReciteMapper;

    /**
     * @param user2CourseControllerParam
     * @return
     * @description 用户选择课程操作
     */
    @Transactional
    @Override
    public ResponseEntity selectPoemCoursePage(User2CourseControllerParam user2CourseControllerParam) {
        Course course = courseMapper.getCourseByCourseId(user2CourseControllerParam.getCourseId());
        /**
         * 查询 如果存在则不需要，如果不存在 需要新增一条数据到用户参与课程
         */
        User2Course userToCourse = user2CourseMapper.selectUser2CourseByUserIdAndCourseId(user2CourseControllerParam.getUid(),user2CourseControllerParam.getCourseId(),user2CourseControllerParam.getTrainingCampId());
        if(userToCourse == null){
            //1、新增一条数据
            userToCourse = new User2Course();
            userToCourse.setUserId(user2CourseControllerParam.getUid());
            userToCourse.setIsDelete(DeleteCode.IS_DELETE_0.getCode());
            userToCourse.setStatus(0);
            userToCourse.setCourseId(user2CourseControllerParam.getCourseId());
            userToCourse.setScoreChoise(0);
            userToCourse.setScoreRecite(0);
            userToCourse.setScoreAll(0);
            userToCourse.setStarLevel(0);
            userToCourse.setTrainingcampid(user2CourseControllerParam.getTrainingCampId());
            int result = user2CourseMapper.insertUser2Course(userToCourse);
            if(result < 0){
                return TouchApiResponse.success("插入数据失败，用户选择课程失败");
            }
        }
        CourseVO courseVO;
        /**课程*/
        if (Integer.parseInt(StaticData.COURSE_TYPE_COURSE) == course.getType()) {
            //0、先查询第一个课程模块id
            List<CourseModule> courseModuleList1=courseModuleService.getCourseModule(user2CourseControllerParam.getSkuId());
            Integer fristCourseModuleId=courseModuleList1.get(0).getId();
            //1、先查询课程模块
            User2CourseModule user2CourseFirstModule=user2CourseModuleMapper.selectUser2CourseFirstModule(user2CourseControllerParam.getUid(),course.getId(),fristCourseModuleId,course.getSkuId());
            //2、如果没有课程模块，数据库增加课程模块
            if(null==user2CourseFirstModule){
                CourseModuleParam courseModuleParam=new CourseModuleParam();
                courseModuleParam.setCourseId(course.getId());
                courseModuleParam.setModuleId(fristCourseModuleId);
                courseModuleParam.setSkuId(course.getSkuId());
                courseModuleParam.setUid(user2CourseControllerParam.getUid());
                int result=user2CourseModuleMapper.insertUser2CourseModule(courseModuleParam);
                if(result<1){
                    return TouchApiResponse.failed("插入（解锁）课程模块失败");
                }
            }
            //关联课程模块
            List<CourseModule> courseModuleList = courseModuleService.getCourseModule(course.getSkuId());
            courseVO = convertor(course);
            //courseModuleList->courseModuleVOList
            List<CourseModuleVO> courseModuleVOList = courseModuleList.stream().map(courseModuleVO -> convertor(courseModuleVO))
                    .collect(Collectors.toList());

            User2CourseModuleVO user2CourseModuleVO = null;
            for (int i = 0; i < courseModuleList.size(); i++) {
                User2CourseModule user2CourseModule = user2CourseModuleService.getUser2CourseModuleByModuleId(user2CourseControllerParam.getUid(), courseModuleList.get(i).getId(), course.getId(),course.getSkuId());
                if (null != user2CourseModule) {
                    user2CourseModuleVO = convertor(user2CourseModule);
                    courseModuleVOList.get(i).setUser2CourseModuleVO(user2CourseModuleVO);
                }  else {
                    user2CourseModuleVO = new User2CourseModuleVO();
                    user2CourseModuleVO.setStatus(CourseModuleStatusCode.COURSE_MODULE_STATUS_2.getCode());
                }
            }

            //根据用户参与模块的数量以及状态来判断解锁状态
            //setUser2CourseStatus(courseModuleVOList, user2CourseControllerParam.getUid(), course.getSkuId());
            //不需要判断解锁状态，因为在第一次点击课程学习时插入第一个模块，第一个模块学习完成更改模块完成状态时插入第二个模块数据，以此类推
            PoertyContentVO poertyContentVO=new PoertyContentVO();
            //查询古诗内容，图片和视频路径
            PoemskuDetail poemskuDetail=new PoemskuDetail();
            poemskuDetail.setCourseId(user2CourseControllerParam.getCourseId());
            poemskuDetail=poemskuDetailMapper.getPoemskuDetailByCourseId(poemskuDetail);
            poertyContentVO.setAudioFull(poemskuDetail.getAudioFull());
            poertyContentVO.setPicContent(poemskuDetail.getPicContent());
            courseVO.setPoertyContentVO(poertyContentVO);
            courseVO.setCourseModuleVOList(courseModuleVOList);
            courseVO.setScoreAll(userToCourse.getScoreAll() == null ? 0 : userToCourse.getScoreAll());
            return TouchApiResponse.success(courseVO);
            /**毕业证书*/
        } else if (Integer.parseInt(StaticData.COURSE_TYPE_DIPLOMA) == course.getType()) {
            /**毕业证书changeType=0*/
            if(user2CourseControllerParam.getChangeType()==0) {
                DiplomaVO diplomaVO = new DiplomaVO();
                //course中的训练营id
                TrainingCamp trainingCamp = trainingCmapService.getTrainingCampById(course.getTrainingCampId());
                //user2course中的uid(训练营ID和用户id)uid,trainingCampId,levelId,subLevelId
                User2TrainingCamp user2TrainingCamp = user2JoinTrainingCampService.userJoinTrainingCampByUidAndTid(user2CourseControllerParam.getUid(), trainingCamp.getId(),course.getLevelId(),course.getSubLevelId());
                //查询个人信息，班级等内容
                User user = userService.getUserById(user2CourseControllerParam.getUid());
                diplomaVO.setType(course.getType());
                diplomaVO.setMessage(StaticData.DIPLOMA_MESSAGE);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(trainingCamp.getName());
                stringBuilder.append(trainingCamp.getPhaseName());
                stringBuilder.append(user2TrainingCamp.getLevelName());
                stringBuilder.append(course.getSubLevelName());
                diplomaVO.setDiplomaInfo(stringBuilder.toString());
                diplomaVO.setUserName(user.getName());
                return TouchApiResponse.success(diplomaVO);
                /**奖状changeType=1*/
            }else if(user2CourseControllerParam.getChangeType()==1){
                CertificateVO certificateVO=new CertificateVO();
                //course中的训练营id
                TrainingCamp trainingCamp = trainingCmapService.getTrainingCampById(course.getTrainingCampId());
                //user2course中的uid(训练营ID和用户id)
                //User2TrainingCamp user2TrainingCamp = user2JoinTrainingCampService.userJoinTrainingCampByUidAndTid(user2CourseControllerParam.getUid(), trainingCamp.getId(),course.getLevelId(),course.getSubLevelId());
                //查询个人信息，班级等内容
                User user = userService.getUserById(user2CourseControllerParam.getUid());

                certificateVO.setType(course.getType());
                StringBuilder sb=new StringBuilder();
                sb.append("你表现极佳掌握了");
                int count=user2CourseCountTest(user2CourseControllerParam.getUid(),course.getTrainingCampId(),course.getLevelId(),course.getSubLevelId(),Integer.parseInt(StaticData.COURSE_TYPE_COURSE));

                /**
                 * @description 结课考试的分数
                 */
                //1、通过uid以及courseId获取用户参与课程分数（结课测试）
                Course course1=courseService.getCourse(trainingCamp.getId(),course.getLevelId(),course.getSubLevelId(),Integer.parseInt(StaticData.END_CLASS_TEST),trainingCamp.getSkuId());
                User2Course user2Course=getUser2Course(user2CourseControllerParam.getUid(),course1.getId(),course.getLevelId());
                String title="";
                Random random=new Random();
                double percent=0.0;
                if(count>=13&user2Course.getScoreAll()>=90){
                    title="古诗小状元";
                        percent=99+random.nextInt(1)/(double)10;
                }else if(13 >=count & count > 10 & 90>=user2Course.getScoreAll()&user2Course.getScoreAll()>75){
                    title="古诗小榜眼";
                    percent=88+random.nextInt(1)/(double)10;
                }else if(10 >=count & count > 7 & 75>=user2Course.getScoreAll()&user2Course.getScoreAll()>=60){
                    title="古诗小探花";
                    percent=77+random.nextInt(1)/(double)10;
                }else{
                    title="古诗小举人";
                    percent=66+random.nextInt(1)/(double)10;
                }
                sb.append(count);
                sb.append("首古诗,超过了");
                sb.append(percent);
                sb.append("%的同学,获得了");
                sb.append(title);
                sb.append("的称号");
                certificateVO.setMessage(sb.toString());
                certificateVO.setMessage1("特发此证,以资鼓励");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(trainingCamp.getName());
                stringBuilder.append(trainingCamp.getPhaseName());
                stringBuilder.append(course.getLevelName());
                stringBuilder.append(course.getSubLevelName());
                certificateVO.setCertificateInfo(stringBuilder.toString());
                certificateVO.setUserName(user.getName());
                certificateVO.setChangeType(user2CourseControllerParam.getChangeType());
                return TouchApiResponse.success(certificateVO);
            }else{
                return TouchApiResponse.failed("等会儿再试！");
            }
            /**周测&&结课测试*/
        } else if(Integer.parseInt(StaticData.COURSE_TYPE_WEEK_TEST)==course.getType()|Integer.parseInt(StaticData.END_CLASS_TEST)==course.getType()){
            WeekTestVO weekTestVO=new WeekTestVO();
            //course中的训练营id
            TrainingCamp trainingCamp =trainingCmapService.getTrainingCampById(course.getTrainingCampId());
            //user2course中的uid(训练营ID和用户id)
            //User2TrainingCamp user2TrainingCamp=user2JoinTrainingCampService.userJoinTrainingCampByUidAndTid(user2CourseControllerParam.getUid(),trainingCamp.getId(),course.getLevelId(),course.getSubLevelId());
            Sku sku=skuService.getSkuIfoById(user2CourseControllerParam.getSkuId());
            weekTestVO.setDescription(trainingCamp.getName()+trainingCamp.getPhaseName()+course.getLevelName()+course.getSubLevelName());
            weekTestVO.setTotalScore((sku.getChoiseNums()*sku.getChoiseScore())+(sku.getReciteNums()*sku.getReciteScore()));
            weekTestVO.setType(course.getType());
            weekTestVO.setTotaltopic(sku.getReciteNums()+sku.getChoiseNums());
            WeekTopicVO weekTopicVO=new WeekTopicVO();
            weekTopicVO.setEveryTestScore(sku.getChoiseScore());
            weekTopicVO.setTopicName(sku.getChoiseTestName());
            weekTopicVO.setTopicNum(sku.getChoiseNums());
            weekTopicVO.setTopicTotalScore(sku.getChoiseNums()*sku.getChoiseScore());

            WeekTopicVO weekTopicVO1=new WeekTopicVO();
            weekTopicVO1.setEveryTestScore(sku.getReciteScore());
            weekTopicVO1.setTopicName(sku.getReciteTestName());
            weekTopicVO1.setTopicNum(sku.getReciteNums());
            weekTopicVO1.setTopicTotalScore(sku.getReciteNums()*sku.getReciteScore());

            List<WeekTopicVO> list =new ArrayList<>();
            list.add(weekTopicVO);
            list.add(weekTopicVO1);
            weekTestVO.setWeekTopicVO(list);
            return TouchApiResponse.success(weekTestVO);
        }
        return TouchApiResponse.failed();
    }


    /**根据用户参与模块的数量以及状态来判断解锁状态
     * 共4个模块
     * 用户参与模块的数量 为4，则直接返回
     * 用户参与模块的数量 为3，则判断第3个模块的参与状态， 已完成，则第四个模块的状态置为已解锁，否则第四个模块状态不做处理。
     * 用户参与模块的数量 为2，则判断第2个模块的参与状态， 已完成，则第三个模块的状态置为已解锁，否则第三个模块状态不做处理。
     * 用户参与模块的数量 为1，则判断第1个模块的参与状态， 已完成，则第二个模块的状态置为已解锁，否则第二个模块状态不做处理。
     * 用户参与模块的数量 为0，则第一个模块状态置为已解锁。
     * courseModuleList 已做排序
     * @param courseModuleList
     */
    private void setUser2CourseStatus(List<CourseModuleVO> courseModuleList, Integer uid, Integer skuId) {
        int moduleNum = courseModuleList.size();
        //用户参与模块数量
        List<User2CourseModule> list = user2CourseModuleService.findUser2CourseModuleList(uid, skuId);
        int user2Num = list.size();
        //用户参与模块数量小于模块总数量时操作，否则即相等，则不做处理
        if(user2Num < moduleNum) {
            if(user2Num == 0){
                CourseModuleVO courseModule = courseModuleList.get(user2Num);
                courseModule.setUser2CourseModuleVO(setUser2CourseModuleStatus(courseModule));
            }else {
                //获取最后一个参与模块的用户参与状态
                User2CourseModule user2CourseModule = list.get(user2Num - 1);
                //如果状态为已完成，则更改下一模块的用户参与状态
                if (user2CourseModule.getStatus() == CourseModuleStatusCode.COURSE_MODULE_STATUS_1.getCode()) {
                    CourseModuleVO courseModule = courseModuleList.get(user2Num);
                    courseModule.setUser2CourseModuleVO(setUser2CourseModuleStatus(courseModule));
                }
            }
        }
    }

    /**
     * @description 用户参与课程状态赋值 解锁状态
     * @param courseModule
     * @return
     */
    private User2CourseModuleVO setUser2CourseModuleStatus(CourseModuleVO courseModule){
        User2CourseModuleVO user2CourseModuleVO = new User2CourseModuleVO();
        user2CourseModuleVO.setStatus(CourseModuleStatusCode.COURSE_MODULE_STATUS_0.getCode());
        return user2CourseModuleVO;
    }

    /**
     * 查询古诗sku-朗诵测试报告
     * @param generalParam
     * @return
     */
    @Override
    public ResponseEntity getReciteReport(GeneralParam generalParam) {
        TestResultVO testResultVO = new TestResultVO();
        TestResult testResult = user2CourseMapper.getTestresultreport(generalParam.getUid(), generalParam.getCourseId());

        TransferUtil.transferIgnoreNull(testResult, testResultVO);

        List<TestResultVO.TopicVO> topicVOList = new ArrayList<TestResultVO.TopicVO>();

        TestResultVO.TopicVO topicChoiseVO = new TestResultVO.TopicVO();
        topicChoiseVO.setName(testResult.getChoiseTestName());
        topicChoiseVO.setScore(testResult.getScoreChoise());

        TestResultVO.TopicVO topicReciteVO = new TestResultVO.TopicVO();
        topicReciteVO.setName(testResult.getReciteTestName());
        topicReciteVO.setScore(testResult.getScoreRecite());
        topicVOList.add(topicChoiseVO);
        topicVOList.add(topicReciteVO);
        testResultVO.setTopicList(topicVOList);

        //获取诗的作者朝代信息
        PoemskuDetail poemskuDetail = poemskuDetailMapper.getPoemskuDetail(generalParam.getUid(), generalParam.getCourseId());

        testResultVO.setPoemName(poemskuDetail.getName());
        testResultVO.setFullName(poemskuDetail.getFullName());
        testResultVO.setAuthor(poemskuDetail.getAuthor());
        testResultVO.setDynasty(poemskuDetail.getDynasty());

        //根据课程获取用户上传的音频数据
        User2ReciteVO user2ReciteVo = user2ReciteService.findByParam(generalParam.getUid(), generalParam.getCourseId());
        user2ReciteVo.setContent(poemskuDetail.getContent());//整首诗的内容
        testResultVO.setUser2ReciteVO(user2ReciteVo);
        return TouchApiResponse.success(testResultVO);
    }

    /**
     * 查询古诗sku-周测测试报告
     * @param generalParam
     * @return
     */
    @Override
    public ResponseEntity getWeekReport(GeneralParam generalParam) {
        TestResultVO testResultVO = new TestResultVO();

        TestResult testResult = user2CourseMapper.getTestresultreport(generalParam.getUid(), generalParam.getCourseId());
        TransferUtil.transferIgnoreNull(testResult, testResultVO);
        List<TestResultVO.TopicVO> topicVOList = new ArrayList<TestResultVO.TopicVO>();
        TestResultVO.TopicVO topicChoiseVO = new TestResultVO.TopicVO();
        topicChoiseVO.setName(testResult.getChoiseTestName());
        topicChoiseVO.setScore(testResult.getScoreChoise());
        TestResultVO.TopicVO topicReciteVO = new TestResultVO.TopicVO();
        topicReciteVO.setName(testResult.getReciteTestName());
        topicReciteVO.setScore(testResult.getScoreChoise());
        topicVOList.add(topicChoiseVO);
        topicVOList.add(topicReciteVO);
        testResultVO.setTopicList(topicVOList);

        //获取周测报告
        List<TestRecite> testReciteList = testReciteMapper.findUser2TestReciteList(generalParam.getUid(), generalParam.getCourseId());
        List<TestReciteVO> volist = new ArrayList<TestReciteVO>();
        TestReciteVO testReciteVO = null;
        for(TestRecite testRecite : testReciteList){
            testReciteVO = new TestReciteVO();
            TransferUtil.transferIgnoreNull(testRecite, testReciteVO);
            volist.add(testReciteVO);
        }
        testResultVO.setTestReciteList(volist);

        return TouchApiResponse.success(testResultVO);
    }

    @Override
    public int updateUser2Course(User2Course user2Course) {
        int result=user2CourseMapper.updateUserCourseScoreAndStatus(user2Course);
        return result;
    }

    /**
     * 更新用户参与课程状态
     * @param generalParam
     * @return
     */
    @Override
    public ResponseEntity updateStatus(GeneralParam generalParam) {
        user2CourseMapper.updateCouresStatus(generalParam.getCourseId(), generalParam.getStatus());
        return TouchApiResponse.success();
    }

    /**
     * @description 用户使用课程模块情况
     * @param user2CourseModule
     * @return
     */
    private User2CourseModuleVO convertor(User2CourseModule user2CourseModule){
        User2CourseModuleVO user2CourseModuleVO=new User2CourseModuleVO();
        user2CourseModuleVO.setStatus(user2CourseModule.getStatus());
        return user2CourseModuleVO;
    }

    /**
     * @description 课程模块情况
     * @param courseModule
     * @return
     */
    private CourseModuleVO convertor(CourseModule courseModule){
        CourseModuleVO courseModuleVO=new CourseModuleVO();
        courseModuleVO.setName(courseModule.getName());
        courseModuleVO.setModuleId(courseModule.getId());
        courseModuleVO.setSort(courseModule.getSort());
        return courseModuleVO;
    }

    /**
     * @descriptiong 课程情况
     * @param course
     * @return
     */
    private CourseVO convertor(Course course){
        CourseVO courseVO=new CourseVO();
        courseVO.setType(course.getType());
        courseVO.setCourseName(course.getName());
        return courseVO;
    }

    /**
     * 用户参与课程模块变更
     * @param courseModuleParam
     * @return
     */
    @Override
    public ResponseEntity updateCourseModule(CourseModuleParam courseModuleParam) {
        //更新当前模块为完成状态
        int result=user2CourseModuleMapper.updateUser2CourseModule(courseModuleParam.getUid(), courseModuleParam.getCourseId(), courseModuleParam.getModuleId(), courseModuleParam.getSkuId());
        if(result>0) {
            //0、插入下一个课程模块
            List<CourseModule> courseModuleList1=courseModuleService.getCourseModule(courseModuleParam.getSkuId());
            //下一个模块id
            Integer nextModuleId=0;
            //循环
            for(int i=0;i<courseModuleList1.size();i++){
                //找到当前模块
                if(courseModuleParam.getModuleId()==courseModuleList1.get(i).getId()){
                    //判断不是最后一个模块
                    if(courseModuleList1.get(courseModuleList1.size()-1).getId()!=courseModuleParam.getModuleId()) {
                        //判断是否已经插入了此模块
                        User2CourseModule user2CourseModule=user2CourseModuleMapper.selectUser2CourseFirstModule(courseModuleParam.getUid(),courseModuleParam.getCourseId(),courseModuleList1.get(i + 1).getId(),courseModuleParam.getSkuId());
                        if(user2CourseModule!=null){
                            return TouchApiResponse.success();
                        }else {
                            nextModuleId = courseModuleList1.get(i + 1).getId();
                            break;
                        }
                    }else{
                        return TouchApiResponse.success();
                    }
                }
            }
            if(0==nextModuleId){
                return TouchApiResponse.failed("解锁失败！！！");
            }
            courseModuleParam.setModuleId(nextModuleId);
            int intsertResult=user2CourseModuleMapper.insertUser2CourseModule(courseModuleParam);
            if(intsertResult>0){
                return TouchApiResponse.success();
            }else{
                return TouchApiResponse.failed("插入下一模块失败");
            }
        }else{
            return TouchApiResponse.failed("更新解锁状态失败");
        }
    }

    /**
     * @description 用户参与课程总数
     * @param uid
     * @param trainingCampId
     * @param subLevelId
     * @return
     */
    @Override
    public int user2CourseCountTest(Integer uid, Integer trainingCampId,Integer levelId, Integer subLevelId,Integer type) {
        int count=user2CourseMapper.selectUserJoinCourseCount(uid,trainingCampId,levelId,subLevelId,type);
        return count;
    }

    /**
     * @description 根据用户id以及课程id进行查询
     * @param uid
     * @param courseId
     * @return
     */
    @Override
    public User2Course getUser2Course(Integer uid, Integer courseId,Integer trainingCampId) {
        User2Course user2Course=user2CourseMapper.selectUser2CourseByUserIdAndCourseId(uid,courseId,trainingCampId);
        return user2Course;
    }

    /**
     * 单课可解锁状态插入用户课程数据
     * @param generalParam
     * @return
     */
    @Override
    public ResponseEntity saveUser2Course(GeneralParam generalParam) {
        User2Course user2Course = new User2Course();
        user2Course.setCourseId(generalParam.getCourseId());
        user2Course.setUserId(generalParam.getUid());
        user2Course.setIsDelete(DeleteCode.IS_DELETE_0.getCode());
        user2Course.setStatus(CourseStatusCode.COURSE_STATUS_0.getCode());
        user2Course.setScoreChoise(0);
        user2Course.setScoreRecite(0);
        user2Course.setScoreAll(0);
        user2Course.setStarLevel(0);
        user2CourseMapper.insertUser2Course(user2Course);
        return TouchApiResponse.success();
    }


}
