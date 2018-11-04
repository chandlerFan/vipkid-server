package com.quxueyuan.bean.domain.UserJoin;

import lombok.Data;

import java.util.Date;

@Data
public class User2TestChoise {
    private Integer id;
    private Integer userid;
    private Integer isDelete;
    private Date updateDate;
    private Date createDate;
    private Integer status;
    private Integer testChoiseId;
    private Integer courseId;
    private String answer;
    private Integer testScore;

}
