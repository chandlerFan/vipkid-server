package com.quxueyuan.server.api.service;

import com.quxueyuan.bean.domain.SysUser;
import com.quxueyuan.bean.vo.res.LoginInfo;

public interface AdminLoginService {

    LoginInfo login(String loginName, String password);

    boolean logout(String ticket);

    LoginInfo getLoginInfo(String loginName, SysUser byNickName);
}
