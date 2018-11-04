package com.quxueyuan.server.api.service.signup;


import com.quxueyuan.bean.domain.signup.Operate;
import com.quxueyuan.bean.vo.req.OperateParam;

public interface OperateService {


    Operate signupqrcodelist(OperateParam operateParam);
}
