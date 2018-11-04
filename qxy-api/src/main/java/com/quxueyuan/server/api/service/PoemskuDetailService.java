package com.quxueyuan.server.api.service;


import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.res.PoemskuDetailVO;
import org.springframework.http.ResponseEntity;

public interface PoemskuDetailService {


	ResponseEntity getrecite(Integer uid, Integer courseId);

	PoemskuDetailVO getPoemskuDetail(Integer uid, Integer courseId);

	ResponseEntity getPageList(Integer page, Integer pageSize, GeneralParam search);
}
