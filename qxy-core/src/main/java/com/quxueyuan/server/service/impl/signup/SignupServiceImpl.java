package com.quxueyuan.server.service.impl.signup;

import com.google.common.collect.Maps;
import com.quxueyuan.bean.domain.UserJoin.User2TrainingCamp;
import com.quxueyuan.bean.domain.signup.Teacher;
import com.quxueyuan.bean.domain.signup.UserTeacher;
import com.quxueyuan.bean.domain.signup.UserTeacherTemp;
import com.quxueyuan.bean.vo.req.SignupParam;
import com.quxueyuan.common.enmu.SignupStatusCode;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.server.api.service.signup.SignupService;
import com.quxueyuan.server.dao.TeacherMapper;
import com.quxueyuan.server.dao.User2TrainingCampMapper;
import com.quxueyuan.server.dao.UserTeacherMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SignupServiceImpl implements SignupService {

    @Resource
    private User2TrainingCampMapper user2TrainingCampMapper;
    @Resource
    private UserTeacherMapper userTeacherMapper;
    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 检查报名状态
     * 0 有效,已报名，已激活  1报名未激活
     * @param signupParam
     * @return
     */
    @Override
    public Map<String, Object> check(SignupParam signupParam) {
        Map<String, Object> map = Maps.newHashMap();
        User2TrainingCamp ut = new User2TrainingCamp();
        TransferUtil.transferIgnoreNull(signupParam, ut);
        ut.setUserId(signupParam.getUid());
        User2TrainingCamp user2TrainingCamp = user2TrainingCampMapper.find(ut);
        if(user2TrainingCamp == null){
            map.put("status", -1);
            map.put("msg", SignupStatusCode.getEnum(-1).getMsg());
            return map;
        }
        int status = user2TrainingCamp.getStatus();//0 有效,已报名，已激活  1报名未激活
        map.put("status", status);
        map.put("msg", SignupStatusCode.getEnum(status).getMsg());
        return map;
    }

    @Transactional
    @Override
    public String signup(SignupParam signupParam) {
        User2TrainingCamp user2TrainingCamp=new User2TrainingCamp();
        user2TrainingCamp.setUserId(signupParam.getUid());
        user2TrainingCamp.setTrainingCampId(signupParam.getTrainingCampId());
        user2TrainingCamp.setTrainingCampName(signupParam.getTrainingCampName());
        user2TrainingCamp.setIsDelete(0);
        user2TrainingCamp.setStatus(0);
        user2TrainingCamp.setLevelId(signupParam.getLevelId());
        user2TrainingCamp.setLevelName(signupParam.getLevelName());
        int row=user2TrainingCampMapper.insertUser2TrainingCamp(user2TrainingCamp);
        UserTeacher userTeacher= new UserTeacher();
        userTeacher.setUserId(signupParam.getUid());
        if(row==0) {

        }
        //1、查询user_teacher表
        List<UserTeacherTemp> userTeacherTemps=userTeacherMapper.selectUser2Teacher();
        if(userTeacherTemps.get(0).getCountStu()<100){
            userTeacher.setTeacherId(userTeacherTemps.get(0).getTeacherId());
        }else{
            List<Integer> list=new ArrayList<>();
            for(UserTeacherTemp userTeacherTemp:userTeacherTemps){
                list.add(userTeacherTemp.getTeacherId());
            }
            List<Integer> teachers=teacherMapper.selectListTeacher();
            teachers.removeAll(list);
            Teacher teacher=teacherMapper.selectTeacherById(teachers.get(0));
            userTeacher.setTeacherId(teacher.getId());
        }
        int result=userTeacherMapper.insertUserTeacher(userTeacher);
        if(result==1){
            //成功
            return teacherMapper.selectTeacherById(userTeacher.getId()).getWechatNo();
        }
        return "报名失败！！！";
    }

}
