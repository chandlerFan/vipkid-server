package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PoemskuDetail {

    private Integer id;
    private Integer courseId;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private String name;
    private String fullName;
    private String author;
    private String dynasty;
    private String content;
    private String picContent;
    private String audioFull;
    private String picSpell;
    private String audioSpell;
    private String picFamousteacher;
    private String audioFamousteacher;

    private List<PoemskuDetailData> list;

}

