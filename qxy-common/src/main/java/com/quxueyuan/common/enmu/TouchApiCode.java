package com.quxueyuan.common.enmu;

/**
 * @Title: TouchApiCode
 * @Company: vipkid
 * @Author liuwei
 * @Date 2018年10月23日 10:18
 * @Description: TODO(用一句话描述该文件做什么)
 */
public enum TouchApiCode {
    TOUCH_API_CODE_9999(9999, "账户未登录或登录TOKEN失效"),

    USER_TRAININGCAMP_IS_NULL(100, "用户没有参加训练营"),
    SUBLEVEL_COURSE_IS_NULL(200, "SubLevel未设置课程"),
    TOPIC_ANSWER_IS_NULL(300, "提交测试题为空"),
    USER_ALREADY_REGISTER(400, "用户已注册"),
    NO_DATE_TO_UP(500, "未到升级日期"),


    TOUCH_API_CODE_0053(0053, "图形验证码不能为空"),
    TOUCH_API_CODE_0054(0054, "图形验证码不正确"),
    TOUCH_API_CODE_0055(0055, "上传文件不可以为空"),


    TOUCH_API_CODE_1000(1000, "用户名不能为空"),
    TOUCH_API_CODE_1001(1001, "密码不能为空"),
    TOUCH_API_CODE_1002(1002, "用户名不存在"),
    TOUCH_API_CODE_1003(1003, "密码不正确"),
    TOUCH_API_CODE_1004(1004, "品类名称已存在"),
    TOUCH_API_CODE_1005(1005, "库存记录不存在"),
    TOUCH_API_CODE_1006(1006, "库存不足"),
    TOUCH_API_CODE_1007(0043, "当前订单商品不存"),
    // 用户新增验证
    TOUCH_API_CODE_1100(1100, "新增用户不能为空"),
    TOUCH_API_CODE_1101(1101, "姓名不能为空"),
    TOUCH_API_CODE_1102(1102, "手机号不能为空"),
    TOUCH_API_CODE_1103(1103, "昵称已存在"),
    TOUCH_API_CODE_1104(1104, "手机号有误"),
    TOUCH_API_CODE_1105(1105, "手机号已存在"),
    TOUCH_API_CODE_1106(1106, "身份证号有误"),
    TOUCH_API_CODE_1107(1107, "身份证号已存在"),
    TOUCH_API_CODE_1108(1108, "微信号已存在"),
    TOUCH_API_CODE_1109(1109, "QQ号已存在"),
    TOUCH_API_CODE_1110(1110, "邮箱号有误"),
    TOUCH_API_CODE_1111(1111, "邮箱号已存在"),
    // 用户修改验证
    TOUCH_API_CODE_1150(1150, "编辑用户不能为空"),
    TOUCH_API_CODE_1151(1151, "ID不能为空"),
    TOUCH_API_CODE_1152(1152, "用户ID不存在"),
    TOUCH_API_CODE_1153(1153, "昵称已存在"),
    TOUCH_API_CODE_1154(1154, "手机号有误"),
    TOUCH_API_CODE_1155(1155, "手机号已存在"),
    TOUCH_API_CODE_1156(1156, "身份证号有误"),
    TOUCH_API_CODE_1157(1157, "身份证号已存在"),
    TOUCH_API_CODE_1158(1158, "微信号已存在"),
    TOUCH_API_CODE_1159(1159, "QQ号已存在"),
    TOUCH_API_CODE_1160(1160, "邮箱号有误"),
    TOUCH_API_CODE_1161(1161, "邮箱号已存在"),
    // 角色新增验证
    TOUCH_API_CODE_1200(1200, "参数对象不能为空"),
    TOUCH_API_CODE_1201(1201, "编码不能为空"),
    TOUCH_API_CODE_1202(1202, "名称不能为空"),
    TOUCH_API_CODE_1203(1203, "项目名称不能为空"),
    TOUCH_API_CODE_1204(1204, "编码已存在"),
    TOUCH_API_CODE_1205(1205, "名称已存在"),
    // 角色修改验证
    TOUCH_API_CODE_1250(1250, "参数对象不能为空"),
    TOUCH_API_CODE_1251(1251, "ID不能为空"),
    TOUCH_API_CODE_1252(1252, "编码已存在"),
    TOUCH_API_CODE_1253(1253, "名称已存在"),

    // 权限新增验证
    TOUCH_API_CODE_1300(1300, "参数对象不能为空"),
    TOUCH_API_CODE_1301(1301, "父节点不能为空"),
    TOUCH_API_CODE_1302(1302, "父节点不存在"),
    TOUCH_API_CODE_1303(1303, "节点名称不能为空"),
    TOUCH_API_CODE_1304(1304, "相同父节点下不能有同名的子节点"),
    // 权限修改验证
    TOUCH_API_CODE_1350(1350, "参数对象不能为空"),
    TOUCH_API_CODE_1351(1351, "ID不能为空"),
    TOUCH_API_CODE_1352(1352, "ID不存在"),
    TOUCH_API_CODE_1353(1353, "父节点不存在"),
    TOUCH_API_CODE_1354(1354, "相同父节点下不能有同名的子节点")

    ;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回结果描述
     */
    private String msg;

    TouchApiCode(Integer code, String msg) {
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
