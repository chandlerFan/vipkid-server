package com.quxueyuan.server.dao;


import com.quxueyuan.bean.AuthLog;

public interface AuthLogMapper {
    int insertSelective(AuthLog record);

    int deleteDataJob(String deleteTime);
}