package com.quxueyuan.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.quxueyuan.bean.domain.PoemskuDetail;
import com.quxueyuan.bean.domain.PoemskuDetailData;
import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.res.PoemskuDetailDataVO;
import com.quxueyuan.bean.vo.res.PoemskuDetailVO;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.server.api.service.CourseModuleService;
import com.quxueyuan.server.api.service.PoemskuDetailService;
import com.quxueyuan.server.dao.CourseMapper;
import com.quxueyuan.server.dao.PoemskuDetailMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class PoemskuDetailServiceImpl implements PoemskuDetailService {

	@Resource
	private PoemskuDetailMapper poemskuDetailMapper;

	@Resource
	private CourseMapper courseMapper;

	@Resource
	private CourseModuleService courseModuleService;

	/**
	 * 查询古诗sku-古诗朗诵页 模块3接口
	 * @param uid, courseId
	 * @return
	 */
	@Override
	public ResponseEntity getrecite(Integer uid, Integer courseId) {

		return TouchApiResponse.success(getPoemskuDetail(uid, courseId));
	}

	@Override
	public PoemskuDetailVO getPoemskuDetail(Integer uid, Integer courseId) {

		//1、查询古诗sku
		//2、查询已经完成朗诵的人数  暂不查询
		PoemskuDetailVO poemskuDetailVO = new PoemskuDetailVO();
		PoemskuDetail poemskuDetail = poemskuDetailMapper.getPoemskuDetail(uid, courseId);
		TransferUtil.transferIgnoreNull(poemskuDetail, poemskuDetailVO);
		poemskuDetailVO.setPoemskuDetailId(poemskuDetail.getId());

		List<PoemskuDetailData> list = poemskuDetailMapper.getPoemskuDetailData(poemskuDetail.getId());
		List<PoemskuDetailDataVO> volist = new ArrayList<PoemskuDetailDataVO>();
		PoemskuDetailDataVO poemskuDetailDataVO = null;
		for(PoemskuDetailData poemskuDetailData : list){
			poemskuDetailDataVO = new PoemskuDetailDataVO();
			TransferUtil.transferIgnoreNull(poemskuDetailData, poemskuDetailDataVO);
			poemskuDetailDataVO.setPoemskuDetailDataId(poemskuDetailData.getId());
			volist.add(poemskuDetailDataVO);
		}
		poemskuDetailVO.setList(volist);
		return poemskuDetailVO;
	}

	/**
	 * 分页查询
	 * @param page
	 * @param pageSize
	 * @param search
	 */
	@Override
	public ResponseEntity getPageList(Integer page, Integer pageSize, GeneralParam search) {
		PageHelper.startPage(page, pageSize);
		Page<PoemskuDetailData> listPage = poemskuDetailMapper.findList();
		List list = listPage.getResult();
		long total = listPage.getTotal();
		int num = listPage.getPageNum();
		int size = listPage.getPageSize();

		return TouchApiResponse.success(listPage);
	}
}
