package com.quxueyuan.bean.vo.res;

import lombok.Data;

@Data
public class PoemskuDetailDataVO {

    private Integer poemskuDetailDataId;
    private Integer poemskuDetailId;
    private Integer courseId;
    private Integer status;
    private String text;
    private String audio;
    private Integer order_idx;

}

