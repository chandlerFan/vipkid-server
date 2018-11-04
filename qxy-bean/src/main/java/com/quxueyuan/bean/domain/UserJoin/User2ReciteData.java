package com.quxueyuan.bean.domain.UserJoin;

import lombok.Data;

import java.util.Date;

@Data
public class User2ReciteData {

    private Integer id;
    private Integer userId;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private Integer courseId;
    private Integer user2reciteId;
    private Integer orderIdx;
    private String audioUrl;
    private Integer poemskuDetailDataId;

    private String text; //分句文本
}
