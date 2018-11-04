package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description 等级数据库对象
 * @author liruichen
 */
@Data
public class Level {
    private Integer id;
    private String name;
    private String isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private Integer skuId;
    private String description;
}
