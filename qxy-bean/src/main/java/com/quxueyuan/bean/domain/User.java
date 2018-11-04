package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description 用户数据库对象
 * @author liruichen
 */
@Data
public class User {
    private Integer id;
    private Integer uid;
    private String name;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private String pic;
    private Integer sex;
}
