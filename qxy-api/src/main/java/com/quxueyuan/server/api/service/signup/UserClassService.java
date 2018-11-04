package com.quxueyuan.server.api.service.signup;


import com.quxueyuan.bean.vo.req.UserClassParam;

public interface UserClassService {

    void activate(UserClassParam userClassParam) throws Exception;
}
