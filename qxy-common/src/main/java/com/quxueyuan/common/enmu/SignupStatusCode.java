package com.quxueyuan.common.enmu;

/**
 * @Title: SignupStatusCode
 * @Company: vipkid
 * @Author liuwei
 * @Date 2018年10月23日 10:18
 * @Description: user2trainingcamp status
 */
public enum SignupStatusCode {

    SIGNUP_1_(-1, "用户未报名"),
    SIGNUP_0(0, "已报名已激活"),
    SIGNUP_1(1, "已报名未激活");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回结果描述
     */
    private String msg;

    SignupStatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static SignupStatusCode getEnum(int code){
        for (SignupStatusCode gpsCheckFlag : SignupStatusCode.values()) {
            if (gpsCheckFlag.getCode().equals(code)) {
                return gpsCheckFlag;
            }
        }
        return null;
    }
}
