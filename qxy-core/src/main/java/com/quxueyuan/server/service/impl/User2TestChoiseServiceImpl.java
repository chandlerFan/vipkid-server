package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.UserJoin.User2TestChoise;
import com.quxueyuan.server.api.service.User2TestChoiseService;
import com.quxueyuan.server.dao.User2TestChoiseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class User2TestChoiseServiceImpl implements User2TestChoiseService {

	@Resource
	private User2TestChoiseMapper user2TestChoiseMapper;

	@Override
	public void saveTestChoiseBatch(List<User2TestChoise> choiseList) {
		for(int i=0;i<choiseList.size();i++){
			User2TestChoise user2TestChoise=user2TestChoiseMapper.selectUser2TestChoise(choiseList.get(i));
			if(null!=user2TestChoise){
				user2TestChoiseMapper.updateUser2TestChoise(choiseList.get(i));
			}else {
				user2TestChoiseMapper.insertUser2TestChoise(choiseList.get(i));
			}
		}
	}

}
