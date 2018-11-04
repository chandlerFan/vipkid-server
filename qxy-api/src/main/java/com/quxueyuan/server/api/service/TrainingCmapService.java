package com.quxueyuan.server.api.service;

import com.quxueyuan.bean.domain.TrainingCamp;
import com.quxueyuan.bean.vo.res.TrainingCmapVO;

import java.util.List;

public interface TrainingCmapService {
    List<TrainingCmapVO> listTrainingCmap();
    TrainingCamp getTrainingCampById(Integer trainingCampId);
}
