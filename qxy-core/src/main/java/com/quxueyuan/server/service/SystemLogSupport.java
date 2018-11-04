package com.quxueyuan.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author liuwei
 * @time 2018/6/28 17:33
 * @description 系统支撑组件
 */
@Slf4j
@Component
public class SystemLogSupport {

//    @Autowired
//    private AccessLogMapper accessLogMapper;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;



    /**
     * 订单操作日志异步添加
     *
     * @param operationId   操作人id
     * @param operationName 操作人姓名
     * @param orderId       订单id
     * @param orderSn       订单编号
     * @param content       订单内容
     * @param orderStatus   订单状态
     */
    public void orderLogSave(Integer operationId, String operationName, Integer orderId, String orderSn, String content, Integer orderStatus) {
//        TaskExecutor taskExecutor = (TaskExecutor) new TaskExecutorConfig();
//        taskExecutor.execute(new Runnable() {
//            @Override
//            @Transactional(rollbackFor = Exception.class)
//            public void run() {
////                OrderLog orderLog = new OrderLog();
////                orderLog.setOperationId(operationId);
////                orderLog.setOperationName(operationName);
////                orderLog.setOrderId(orderId);
////                orderLog.setOrderSn(orderSn);
////                orderLog.setContent(content);
////                orderLog.setOrderStatus(orderStatus);
////                orderLog.setCreateTime(new Date());
////                orderLogMapper.insert(orderLog);
//                log.info("订单操作日志异步添加成功");
//            }
//        });
    }

    /**
     * 订单操作日志异步添加 批量
     *
     * @param listMap
     * @param content       订单内容
     * @param orderStatus   订单状态
     */
    public void orderLogBatchSave(List<Map<String, Object>> listMap, String content, int orderStatus) {
        for(Map<String, Object> map : listMap){
            orderLogSave(null, null, Integer.parseInt(map.get("order_id").toString()), map.get("order_sn").toString(), content, orderStatus);
        }
    }
}
