package com.quxueyuan.bean.vo.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 训练营展示对象
 */
@Data
public class TrainingCmapVO implements Serializable {
    //训练营id
    private Integer id;
    //训练营名称
    private String name;
    //学科id
    private Integer subjectId;
    //描述
    private String description;
    //学科名称
    private String subjectName;
    //sku Id
    private Integer skuId;
    //sku 名称
    private String skuName;
    //学期名称
    private String phaseName;
    //开始时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date startDate;
    //结束名称
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date endDate;
    //level名称
    private String levelName;
    //等级描述
    private String levelDescription;
    //level skuId
    private Integer levelId;
}
