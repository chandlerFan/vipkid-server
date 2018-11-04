package com.quxueyuan.bean.vo.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //属性值为空则不返回
public class PoemskuDetailVO {

    private Integer poemskuDetailId;
    private Integer courseId;
    private Integer isDelete;
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

    private List<PoemskuDetailDataVO> list;

}

