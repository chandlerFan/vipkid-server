package com.quxueyuan.server.dao;


import com.quxueyuan.bean.mapper.MQErrorLog;

/**
 * @author lagon
 * @time 2017/6/27 18:02
 * @description MQ错误日志DAO
 */
public interface MQErrorLogMapper {

    /**
     * 保存MQ错误日志
     * @param mqErrorLogPO
     */
    void saveMQErrorLog(MQErrorLog mqErrorLogPO);

}
