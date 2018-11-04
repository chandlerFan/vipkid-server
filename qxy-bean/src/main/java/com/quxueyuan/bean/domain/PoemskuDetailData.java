package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PoemskuDetailData {

    private Integer id;
    private Integer poemskuDetailId;
    private Integer courseId;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private String text;
    private String audio;
    private Integer order_idx;

}

