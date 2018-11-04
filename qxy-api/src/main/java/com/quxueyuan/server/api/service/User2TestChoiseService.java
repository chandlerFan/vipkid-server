package com.quxueyuan.server.api.service;


import com.quxueyuan.bean.domain.UserJoin.User2TestChoise;

import java.util.List;


/**
 * @description 用户选择题操作
 * @author liuwei
 */
public interface User2TestChoiseService {


    void saveTestChoiseBatch(List<User2TestChoise> choiseList);
}
