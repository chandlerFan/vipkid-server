package com.quxueyuan.common.enmu;

/**
 * @Title: CourseModuleStatusCode
 * @Company: vipkid
 * @Author liuwei
 * @Date 2018年10月23日 10:18
 * @Description: TODO(用一句话描述该文件做什么)
 */
public enum CourseModuleStatusCode {

    COURSE_MODULE_STATUS_0(0, "解锁"), //正在进行
    COURSE_MODULE_STATUS_1(1, "完成"), //已完成
    COURSE_MODULE_STATUS_2(2, "锁定"); //未完成
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回结果描述
     */
    private String msg;

    CourseModuleStatusCode(Integer code, String msg) {
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
