package com.quxueyuan.common.enmu;

/**
 * @Title: DeleteCode
 * @Company: vipkid
 * @Author liuwei
 * @Date 2018年10月23日 10:18
 * @Description: TODO(用一句话描述该文件做什么)
 */
public enum DeleteCode {

    IS_DELETE_0(0, "有效"),
    IS_DELETE_1(1, "无效");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回结果描述
     */
    private String msg;

    DeleteCode(Integer code, String msg) {
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
