package com.quxueyuan.bean.domain.UserJoin;

import lombok.Data;

import java.util.Date;

/**
 * @description 用户参与课程数据库信息
 * @author liruichen
 */
@Data
public class User2Course {
    private Integer id;
    private Integer userId;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private Integer courseId;
    private Integer scoreChoise;
    private Integer scoreRecite;
    private Integer scoreAll;
    private Integer starLevel;
    private Integer trainingcampid;

}
