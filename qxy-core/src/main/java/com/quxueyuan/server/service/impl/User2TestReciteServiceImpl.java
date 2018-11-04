package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.UserJoin.User2TestRecite;
import com.quxueyuan.server.api.service.User2TestReciteService;
import com.quxueyuan.server.dao.User2TestReciteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class User2TestReciteServiceImpl implements User2TestReciteService {

    @Resource
    private User2TestReciteMapper user2TestReciteMapper;

    @Override
    public void saveTestReciteBatch(List<User2TestRecite> reciteList) {
        for(int i=0;i<reciteList.size();i++){
        User2TestRecite user2TestRecite=user2TestReciteMapper.selectUser2TestRecite(reciteList.get(i));
        if(null!=user2TestRecite){
            user2TestReciteMapper.updateUser2TestRecite(reciteList.get(i));
        }else {
            user2TestReciteMapper.insertUser2TestRecite(reciteList.get(i));
        }
    }
}
}
