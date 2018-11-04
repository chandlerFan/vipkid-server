package com.quxueyuan.bean.domain.UserJoin;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User2Recite {

    private Integer id;
    private Integer userid;
    private Integer isDelete;
    private Date createDate;
    private Date updateDate;
    private Integer status;
    private Integer courseid;
    private String audioFull;
    private Integer poemskuDetailId;
    private List<User2ReciteData> user2ReciteDataList;

}
