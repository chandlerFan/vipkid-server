package com.quxueyuan.server.api.service.signup;


import com.quxueyuan.bean.vo.req.LikeParam;

public interface LikeService {

    int like(LikeParam likeParam) throws Exception;
}
