package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 用户参与训练营前台实体类
 * @author liruichen
 */
@Data
public class UserJoinTrainingCampVO implements Serializable {
    private Integer userId;
    private Integer trainingCampId;
    private String trainingCampName;
    private Integer levelId;
    private String levelName;

}
