package com.quxueyuan.server.service.impl.signup;


import com.quxueyuan.bean.domain.signup.Operate;
import com.quxueyuan.bean.vo.req.OperateParam;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.server.api.service.signup.OperateService;
import com.quxueyuan.server.dao.OperateMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OperateServiceImpl implements OperateService {

    @Resource
    OperateMapper operateMapper;

    /**
     * 查询训运营功能表获取当期训练营报名二维码
     * @param operateParam
     * @return
     */
    @Override
    public Operate signupqrcodelist(OperateParam operateParam) {
        Operate temp = new Operate();
        TransferUtil.transferIgnoreNull(operateParam, temp);
        Operate operate = operateMapper.findByParam(temp);
        return operate;
    }
}
