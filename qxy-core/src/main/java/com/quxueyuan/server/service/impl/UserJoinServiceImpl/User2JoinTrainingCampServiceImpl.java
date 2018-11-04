package com.quxueyuan.server.service.impl.UserJoinServiceImpl;

import com.quxueyuan.bean.domain.UserJoin.User2TrainingCamp;
import com.quxueyuan.bean.vo.req.UserJoinTrainingCmapParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.common.util.StaticData;
import com.quxueyuan.server.api.service.UserJoinService.User2JoinTrainingCampService;
import com.quxueyuan.server.dao.User2TrainingCampMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class User2JoinTrainingCampServiceImpl implements User2JoinTrainingCampService {

    @Resource
    private User2TrainingCampMapper user2TrainingCampMapper;
    /**
     * @description 用户参与训练营业务层
     * @param userJoinTrainingCmapParam
     * @author liruichen
     * @return
     */
    @Override
    public ResponseEntity userJoinTrainingCamp(UserJoinTrainingCmapParam userJoinTrainingCmapParam) {
        //1、判断用户室友已经参与这个训练营
        User2TrainingCamp user2TrainingCamp = user2TrainingCampMapper.findUser2Training(userJoinTrainingCmapParam.getUid(), userJoinTrainingCmapParam.getTrainingCampId(), userJoinTrainingCmapParam.getLevelId());
        if(user2TrainingCamp == null){
            user2TrainingCamp = new User2TrainingCamp();
            user2TrainingCamp.setUserId(userJoinTrainingCmapParam.getUid());
            user2TrainingCamp.setTrainingCampId(userJoinTrainingCmapParam.getTrainingCampId());
            user2TrainingCamp.setTrainingCampName(userJoinTrainingCmapParam.getTrainingCampName());
            user2TrainingCamp.setIsDelete(Integer.parseInt(StaticData.IS_NOT_DELETE));
            user2TrainingCamp.setStatus(Integer.parseInt(StaticData.STATUS_NORMAL));
            user2TrainingCamp.setLevelId(userJoinTrainingCmapParam.getLevelId());
            user2TrainingCamp.setLevelName(userJoinTrainingCmapParam.getLevelName());
            int result=user2TrainingCampMapper.insertUser2TrainingCamp(user2TrainingCamp);
            if(result>0){
                return TouchApiResponse.success();
            }else{
                return TouchApiResponse.failed("用户参与"+userJoinTrainingCmapParam.getTrainingCampName()+"训练营失败！");
            }
        }

        return TouchApiResponse.success("用户已参与成功！");
    }

    @Override
    public User2TrainingCamp userJoinTrainingCampByUidAndTid(Integer uid, Integer trainingCampId,Integer levelId,Integer sublevelId) {
        User2TrainingCamp user2TrainingCamp=user2TrainingCampMapper.selectUser2TrainingCampByUidAndTrainingCampId(uid,trainingCampId,levelId,sublevelId);
        return user2TrainingCamp;
    }
}
