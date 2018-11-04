package com.quxueyuan.bean.vo.res;

import lombok.Data;

/**
 * @description 奖状前台参数
 */
@Data
public class CertificateVO {
    private Integer type;
    private String CertificateInfo;
    private String message;
    private String message1;
    private String userName;
    private Integer changeType;
}
