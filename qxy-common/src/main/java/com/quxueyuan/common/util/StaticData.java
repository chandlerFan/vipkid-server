package com.quxueyuan.common.util;

import lombok.Data;

/**
 * @description 静态常量
 */
@Data
public class StaticData {

    /**已删除*/
    public static final String IS_DELETE="1";
    /**未删除*/
    public static final String IS_NOT_DELETE="0";
    /**状态正常*/
    public static final String STATUS_NORMAL="0";
    /**状态异常*/
    public static final String STATUS_ABNORMAL="1";
    /**课程类型（课程）*/
    public static final String COURSE_TYPE_COURSE="1";
    /**课程类型（周测）*/
    public static final String COURSE_TYPE_WEEK_TEST="2";
    /**课程类型（结果测试）*/
    public static final String END_CLASS_TEST="3";
    /**课程类型（奖状）*/
    public static final String COURSE_TYPE_DIPLOMA="4";
    /**毕业证书message*/
    public static final String DIPLOMA_MESSAGE="你表现优于,准予毕业,特发此毕业证书.";
}
