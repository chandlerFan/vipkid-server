package com.quxueyuan.common.enmu;

/**
 * @Title: CourseStatusCode
 * @Company: vipkid
 * @Author liuwei
 * @Date 2018年10月23日 10:18
 * @Description: TODO(用一句话描述该文件做什么)
 */
public enum CourseTypeCode {

    COURSE_TYPE_1(1, "课程"),
    COURSE_TYPE_2(2, "周测"),
    COURSE_TYPE_3(3, "结课测试"),
    COURSE_TYPE_4(4, "奖状");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回结果描述
     */
    private String msg;

    CourseTypeCode(Integer code, String msg) {
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
