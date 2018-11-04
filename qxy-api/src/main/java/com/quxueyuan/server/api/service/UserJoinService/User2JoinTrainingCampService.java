package com.quxueyuan.server.api.service.UserJoinService;

import com.quxueyuan.bean.domain.UserJoin.User2TrainingCamp;
import com.quxueyuan.bean.vo.req.UserJoinTrainingCmapParam;
import org.springframework.http.ResponseEntity;

public interface User2JoinTrainingCampService {
    ResponseEntity userJoinTrainingCamp(UserJoinTrainingCmapParam userJoinTrainingCmapParam);

    User2TrainingCamp userJoinTrainingCampByUidAndTid(Integer uid, Integer trainingCampId, Integer levelId, Integer sublevelId);

}
