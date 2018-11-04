package com.quxueyuan.server.service.impl.signup;


import com.quxueyuan.bean.domain.signup.User2share;
import com.quxueyuan.bean.vo.req.ShareParam;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.common.util.date.DateUtil;
import com.quxueyuan.server.api.service.signup.ShareService;
import com.quxueyuan.server.dao.ShareMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShareServiceImpl implements ShareService {

    @Resource
    private ShareMapper shareMapper;

    /**
     * 检查报名状态
     * @param shareParam
     * @return
     */
    @Override
    public void share(ShareParam shareParam) {
        User2share us = new User2share();
        TransferUtil.transferIgnoreNull(shareParam, us);
        us.setShareDate(DateUtil.getDateTime());
        us.setStatus(0);
        shareMapper.insert(us);
    }

}
