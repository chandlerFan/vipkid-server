package com.quxueyuan.bean.vo.req;

import lombok.Data;

@Data
public class LikeParam {
    private Integer courseId;
    private Integer uidFrom;
    private Integer uidTo;
    private Integer skuId;
}
