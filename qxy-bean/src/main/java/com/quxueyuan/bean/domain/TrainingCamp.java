package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description 训练营数据对象
 * @author liruichen
 */
@Data
public class TrainingCamp {
    //主键 id
    private Integer id;
    //训练营名称
    private String name;
    //是否已经被删除1是删除，0是没有被删除
    private Integer isDelete;
    //创建时间
    private Date createDate;
    //更新时间
    private Date updateDate;
    //状态
    private Integer status;
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
    private Date startDate;
    //结束名称
    private Date endDate;
    
    
    
}
