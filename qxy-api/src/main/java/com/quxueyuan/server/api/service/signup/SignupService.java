package com.quxueyuan.server.api.service.signup;


import com.quxueyuan.bean.vo.req.SignupParam;

import java.util.Map;


public interface SignupService {

    Map<String, Object> check(SignupParam signupParam);

    String signup(SignupParam signupParam);
}
