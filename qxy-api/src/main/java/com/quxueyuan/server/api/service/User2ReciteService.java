package com.quxueyuan.server.api.service;

import com.quxueyuan.bean.vo.req.AuditionrecitationParam;
import com.quxueyuan.bean.vo.req.CommitReciteParam;
import com.quxueyuan.bean.vo.req.CommitrecitationParam;
import com.quxueyuan.bean.vo.res.User2ReciteVO;
import org.springframework.http.ResponseEntity;

public interface User2ReciteService {

    ResponseEntity commitrecitationWx(CommitrecitationParam commitrecitationParam);

    ResponseEntity commitrecitationApp(CommitrecitationParam commitrecitationParam);

    ResponseEntity auditionrecitation(AuditionrecitationParam auditionrecitationParam);

    ResponseEntity commitreciteWx(CommitReciteParam commitReciteParam);

    ResponseEntity commitreciteApp(CommitrecitationParam commitrecitationParam);

    User2ReciteVO findByParam(Integer uid, Integer courseId);
}
