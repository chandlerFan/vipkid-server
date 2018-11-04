package com.quxueyuan.bean.domain;

import lombok.Data;

@Data
public class TestResult {
    private String name;
    private String pic;
    private String choiseTestName;
    private String reciteTestName;
    private Integer scoreChoise;
    private Integer scoreRecite;
    private Integer scoreAll;
    private Integer courseId;
    private Integer starLevel;
}
