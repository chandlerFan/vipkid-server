package com.quxueyuan.bean.vo.res;

import lombok.Data;

@Data
public class WeekTopicVO {
    private String topicName;
    private Integer topicNum;
    private Integer everyTestScore;
    private Integer topicTotalScore;//topicNum*everyTestScore
}
