package com.quxueyuan.bean.vo.req;

import lombok.Data;

import java.util.List;

@Data
public class User2TestCommitParam {
    private Integer uid;
    private Integer courseId;
    private Integer trainingcampId;
    private Integer skuId;
    private List<TopicAnswer> testTopicList;

    @Data
    public static class TopicAnswer {
        private String answer; //选择题答案
        private Integer testTopicId; //测试题id
        private Integer type; // 1、选择题，2、朗诵题、TopicTypeCode枚举类型
        private Integer status; // 用户答题最终状态（正确1、错误0）
        private Integer testScore;// 朗诵题得分&选择题得分
    }
}
