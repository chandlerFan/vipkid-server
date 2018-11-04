package com.quxueyuan.bean.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description 用户
 * @author liuwei
 */
@Data
public class UserLogin {
    private Integer id;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private String phone;
    private String password;
    private Integer status;
    private String openId;
    private String unionid;
    private Integer source;
}
