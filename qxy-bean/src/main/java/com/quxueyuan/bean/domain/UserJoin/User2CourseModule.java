package com.quxueyuan.bean.domain.UserJoin;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User2CourseModule implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private Integer moduleId;
}
