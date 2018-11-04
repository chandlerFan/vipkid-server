package com.quxueyuan.server.api.service;


import com.quxueyuan.bean.domain.User;
import com.quxueyuan.bean.domain.UserLogin;
import com.quxueyuan.bean.vo.req.GetOpenIdParam;
import com.quxueyuan.bean.vo.req.UserLoginParam;
import com.quxueyuan.bean.vo.req.UserParam;
import com.quxueyuan.bean.vo.req.UserRegisterParam;
import org.springframework.http.ResponseEntity;

/**
 * @description 用户业务层
 * @author liruichen
 */
public interface UserService {

	User getUserById(Integer id);

//    AddressingFeature.Responses updateUser(UserParam userParam);

    int addUser(UserParam userParam);

    ResponseEntity register(UserRegisterParam userRegisterParam);

    ResponseEntity login(UserLoginParam userLoginParam);

    ResponseEntity auth(String url) throws Exception;

    ResponseEntity getopenid(GetOpenIdParam getOpenIdParam) throws Exception;

    UserLogin getUserByOpenId(String openid);
}
