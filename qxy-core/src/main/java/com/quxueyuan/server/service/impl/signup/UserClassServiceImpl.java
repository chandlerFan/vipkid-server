package com.quxueyuan.server.service.impl.signup;


import com.quxueyuan.bean.domain.UserJoin.User2TrainingCamp;
import com.quxueyuan.bean.domain.signup.UserClass;
import com.quxueyuan.bean.vo.req.UserClassParam;
import com.quxueyuan.common.enmu.SignupStatusCode;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.common.util.date.DateUtil;
import com.quxueyuan.server.api.service.signup.UserClassService;
import com.quxueyuan.server.dao.User2TrainingCampMapper;
import com.quxueyuan.server.dao.UserClassMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserClassServiceImpl implements UserClassService {

    @Resource
    private User2TrainingCampMapper user2TrainingCampMapper;

    @Resource
    private UserClassMapper userClassMapper;


    /**
     * 激活报名
     * @param userClassParam
     * @return
     */
    @Transactional
    @Override
    public void activate(UserClassParam userClassParam) throws Exception{
        User2TrainingCamp ut = new User2TrainingCamp();
        TransferUtil.transferIgnoreNull(userClassParam, ut);
        ut.setUserId(userClassParam.getUid());
        User2TrainingCamp user2TrainingCamp = user2TrainingCampMapper.find(ut);
        user2TrainingCamp.setStatus(SignupStatusCode.SIGNUP_0.getCode());
        //更改报名状态
        user2TrainingCampMapper.updateStatus(user2TrainingCamp);

        //添加用户班级表数据
        UserClass userClass = new UserClass();
        userClass.setClassId(userClassParam.getClassId());
        userClass.setUserId(userClassParam.getUid());
        userClass.setJoinDate(DateUtil.getDateTime());
        userClassMapper.insert(userClass);
    }
}
