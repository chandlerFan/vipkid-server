package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.Level;
import com.quxueyuan.bean.domain.TrainingCamp;
import com.quxueyuan.bean.vo.res.TrainingCmapVO;
import com.quxueyuan.server.api.service.TrainingCmapService;
import com.quxueyuan.server.dao.LevelMapper;
import com.quxueyuan.server.dao.TrainingCmapMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class TrainingCmapServiceImpl implements TrainingCmapService {

    @Resource
    private TrainingCmapMapper trainingCmapMapper;
    @Resource
    private LevelMapper levelMapper;

    @Override
    public List<TrainingCmapVO> listTrainingCmap() {
        List<TrainingCamp> list=trainingCmapMapper.getTrainingCmap();
        List<TrainingCmapVO> trainingCmaplist=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            List<Level>levelList=levelMapper.getLevelList(list.get(i).getSkuId());
            for(int y=0;y<levelList.size();y++){
                TrainingCmapVO tc=new TrainingCmapVO();
                tc.setId(list.get(i).getId());
                tc.setSubjectId(list.get(i).getSubjectId());
                tc.setSubjectName(list.get(i).getSubjectName());
                tc.setSkuId(list.get(i).getSkuId());
                tc.setSkuName(list.get(i).getSkuName());
                tc.setName(list.get(i).getName());
                tc.setDescription(list.get(i).getDescription());
                tc.setPhaseName(list.get(i).getPhaseName());
                tc.setStartDate(list.get(i).getStartDate());
                tc.setEndDate(list.get(i).getEndDate());
                tc.setLevelName(levelList.get(y).getName());
                tc.setLevelDescription(levelList.get(y).getDescription());
                tc.setSkuId(list.get(i).getSkuId());
                tc.setLevelId(levelList.get(y).getId());
                trainingCmaplist.add(tc);
            }

        }
        return trainingCmaplist;
    }


    @Override
    public TrainingCamp getTrainingCampById(Integer trainingCampId){
        TrainingCamp trainingCamp=trainingCmapMapper.selectTrainingCampById(trainingCampId);
        return trainingCamp;
    }
}
