package com.quxueyuan.server.service.impl;

import com.quxueyuan.bean.domain.SysUser;
import com.quxueyuan.bean.domain.SysUserQuery;
import com.quxueyuan.server.api.IdemoService;
import com.quxueyuan.server.api.service.SysUserService;
import com.quxueyuan.server.dao.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liuwei
 * @date: 2018/7/15 13:14
 * @version: 1.0
 */
@Service
public class DemoServiceImpl implements IdemoService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void adminDemo() {
        System.out.println("admin out ...");

        SysUserQuery query = new SysUserQuery();
        List<SysUser> list = sysUserMapper.selectByExample(query);
        System.out.println("---------------------------------------------------");



    }

    @Override
    public void appDemo() {
        System.out.println("app out ...");
    }

    @Override
    public void touchDemo() {
        System.out.println("touch out ...");
    }
}
