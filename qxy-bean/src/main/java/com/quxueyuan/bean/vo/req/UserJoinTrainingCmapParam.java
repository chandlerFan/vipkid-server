package com.quxueyuan.bean.vo.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 用户参与训练营前台传递实体对象
 * @author liruichen
 */
@Data
public class UserJoinTrainingCmapParam implements Serializable {

    private Integer uid;//用户Id
    private Integer trainingCampId;//用户训练营Id
    private String trainingCampName;//用户训练营名称
    private Integer levelId;//用户等级Id
    private String levelName;//用户等级名称

}
