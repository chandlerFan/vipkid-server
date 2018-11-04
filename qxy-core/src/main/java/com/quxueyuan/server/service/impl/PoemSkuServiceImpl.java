package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.PoemskuDetail;
import com.quxueyuan.bean.vo.req.CourseIdParam;
import com.quxueyuan.bean.vo.res.PoemskuCourseModule1Vo;
import com.quxueyuan.bean.vo.res.PoemskuCourseModule2Vo;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.server.api.service.PoemSkuService;
import com.quxueyuan.server.dao.PoemskuDetailMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public  class PoemSkuServiceImpl implements PoemSkuService {
	
	@Resource
    private PoemskuDetailMapper poemskuDetailMapper;

	/**
	 * 查询 古诗sku-拼音讲解页- 模块1
	 * @param courseIdParam
	 * @return
	 */
	@Override
	public ResponseEntity getSpellModule(CourseIdParam courseIdParam) {
		PoemskuDetail poemskuDetail=poemskuDetailMapper.getPoemskuDetail(courseIdParam.getUid(), courseIdParam.getCourseId());
		PoemskuCourseModule1Vo poemskuCourseModule1Vo = getPoemskuCoursemodule1Vo(poemskuDetail);
		return TouchApiResponse.success(poemskuCourseModule1Vo);
		
	}
	
	public PoemskuCourseModule1Vo getPoemskuCoursemodule1Vo(PoemskuDetail poemskuDetail) {
		PoemskuCourseModule1Vo poemskuCourseModule1Vo = new PoemskuCourseModule1Vo();
		TransferUtil.transferIgnoreNull(poemskuDetail, poemskuCourseModule1Vo);
		poemskuCourseModule1Vo.setCoursemoduleId(1);
		poemskuCourseModule1Vo.setDynastyAuthor(poemskuDetail.getDynasty()+":"+poemskuDetail.getAuthor());
        return poemskuCourseModule1Vo;
    }

	/**
	 * 查询 古诗sku-名师讲古诗页- 模块2
	 * @param courseIdParam
	 * @return
	 */
	@Override
	public ResponseEntity getFamouseTeacher(CourseIdParam courseIdParam) {
		PoemskuDetail poemskuDetail=poemskuDetailMapper.getPoemskuDetail(courseIdParam.getUid(), courseIdParam.getCourseId());
		PoemskuCourseModule2Vo poemskuCourseModule2Vo = getPoemskuCoursemodule2Vo(poemskuDetail);
		return TouchApiResponse.success(poemskuCourseModule2Vo);
	}

	public PoemskuCourseModule2Vo  getPoemskuCoursemodule2Vo(PoemskuDetail poemskuDetail) {
        
		PoemskuCourseModule2Vo poemskuCourseModule2Vo = new PoemskuCourseModule2Vo();
		TransferUtil.transferIgnoreNull(poemskuDetail, poemskuCourseModule2Vo);
		poemskuCourseModule2Vo.setCoursemoduleId(2);
		poemskuCourseModule2Vo.setDynastyAuthor(poemskuDetail.getDynasty()+":"+poemskuDetail.getAuthor());
        return poemskuCourseModule2Vo;
    }

}
