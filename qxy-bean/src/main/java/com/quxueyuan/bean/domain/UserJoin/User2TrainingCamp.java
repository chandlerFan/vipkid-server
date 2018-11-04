package com.quxueyuan.bean.domain.UserJoin;


import lombok.Data;

import java.sql.Date;


/**
 * @description 用户参与训练营情况数据库对象
 * @author liruichen
 */
@Data
public class User2TrainingCamp {
    private Integer id;
    private Integer userId;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private Integer trainingCampId;
    private String trainingCampName;
    private Integer levelId;
    private String levelName;
    private Integer sublevelId;
    private String sublevelName;

}
