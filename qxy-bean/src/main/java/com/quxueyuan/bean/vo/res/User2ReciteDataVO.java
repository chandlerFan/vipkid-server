package com.quxueyuan.bean.vo.res;

import lombok.Data;

@Data
public class User2ReciteDataVO {

    private Integer user2ReciteDataId;
    private Integer userId;
    private Integer status;
    private Integer courseId;
    private Integer user2reciteId;
    private Integer orderIdx;
    private String audioUrl;
    private Integer poemskuDetailDataId;

    private String text; //分句文本
}
