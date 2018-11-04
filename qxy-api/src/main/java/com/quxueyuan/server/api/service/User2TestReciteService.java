package com.quxueyuan.server.api.service;



import com.quxueyuan.bean.domain.UserJoin.User2TestRecite;

import java.util.List;

/**
 * @description 用户朗诵题操作
 * @author liuwei
 */
public interface User2TestReciteService {


    void saveTestReciteBatch(List<User2TestRecite> reciteList);
}
