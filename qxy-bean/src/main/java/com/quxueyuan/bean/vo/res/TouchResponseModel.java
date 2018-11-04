package com.quxueyuan.bean.vo.res;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class TouchResponseModel {

//    private Object data = null;
//    private String errorCode = "";
//    private String msg = null;
//    private String result = "1";


    private Status status;
    private Object result;

    @ToString
    @Data
    public static class Status {
        private int code;//成功0，失败1
        private String description;
    }


}

