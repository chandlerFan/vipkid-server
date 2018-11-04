package com.quxueyuan.server.service.impl.signup;


import com.quxueyuan.bean.domain.signup.User2like;
import com.quxueyuan.bean.vo.req.LikeParam;
import com.quxueyuan.server.api.service.signup.LikeService;
import com.quxueyuan.server.dao.LikeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LikeServiceImpl implements LikeService {


    @Resource
    private LikeMapper likeMapper;

    @Override
    public int like(LikeParam likeParam) throws Exception{

        User2like user2like=new User2like();
        user2like.setCourseId(likeParam.getCourseId());
        user2like.setUidFrom(likeParam.getUidFrom());
        user2like.setUidTo(likeParam.getUidTo());
        user2like.setSkuId(likeParam.getSkuId());
        int row=likeMapper.insertUser2Like(user2like);
        if(row==1){
            return 1;
        }
        return 0;
    }
}
