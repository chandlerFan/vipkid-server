package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.util.List;

@Data
public class WeekTestVO {
    private String description;
    private Integer type;
    private Integer totalScore;//总分数
    private Integer totaltopic;//总题数
    private List<WeekTopicVO> weekTopicVO;
}
