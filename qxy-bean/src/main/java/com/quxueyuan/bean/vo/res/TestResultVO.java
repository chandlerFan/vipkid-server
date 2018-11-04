package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TestResultVO implements Serializable {
    
	private String name;
    private String pic;
    private Integer scoreAll;
    private Integer courseId;
    private Integer starLevel;
    private String poemName;
    private String fullName;
    private String author;
    private String dynasty;
    private User2ReciteVO user2ReciteVO;

    private List<TopicVO> topicList;

    @Data
    public static class TopicVO{
        private String name;
        private Integer score;
    }


    List<TestReciteVO> testReciteList;//周测报告客户答题信息
}
