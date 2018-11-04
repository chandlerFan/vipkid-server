package com.quxueyuan.common.enmu;

/**
 * @Title: LevelStatusCode
 * @Company: vipkid
 * @Author liuwei
 * @Date 2018年10月23日 10:18
 * @Description: TODO(用一句话描述该文件做什么)
 */
public enum LevelStatusCode {

    LOCK_OPEN(0, "解锁"),
    LOCK_CLOSE(1, "锁定");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回结果描述
     */
    private String msg;

    LevelStatusCode(Integer code, String msg) {
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
}
