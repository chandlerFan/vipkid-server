package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.TestRecite;
import com.quxueyuan.bean.domain.UserJoin.User2Recite;
import com.quxueyuan.bean.domain.UserJoin.User2ReciteData;
import com.quxueyuan.bean.domain.UserJoin.User2TestRecite;
import com.quxueyuan.bean.vo.req.AuditionrecitationParam;
import com.quxueyuan.bean.vo.req.CommitReciteParam;
import com.quxueyuan.bean.vo.req.CommitrecitationParam;
import com.quxueyuan.bean.vo.res.PoemRecicationVo;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.bean.vo.res.User2ReciteVO;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.server.api.service.User2ReciteService;
import com.quxueyuan.server.dao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class User2ReciteServiceImpl implements User2ReciteService {

    @Resource
    private User2ReciteMapper user2ReciteMapper;

	@Resource
	private TestReciteMapper testReciteMapper;
	@Resource
	private User2CourseModuleMapper user2CourseModuleMappper;

	@Resource
	private User2TestReciteMapper user2TestReciteMapper;

    /**
     * 微信提交音频文件
     * @param commitrecitationParam
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity commitrecitationWx(CommitrecitationParam commitrecitationParam) {
        //1、通过微信openID、resourcesUrl 获取资源
        //2、将资源上传至文件存储服务器
		String resourcesUrl = commitrecitationParam.getResourcesUrl();
		String url = "http://www.baidu.com"; //上传音频到服务器后的地址
        //3、将信息插入user2recite
    	//4  如果是最后一个 完整朗诵 ，更新 用户参与课程模块状态

		//判断user2recite 是否有数据，第一次存储音频是分句，但需要先在user2recite插入一条基本数据
		User2Recite user2Recite = new User2Recite();
		user2Recite.setUserid(commitrecitationParam.getUid());
		user2Recite.setCourseid(commitrecitationParam.getCourseId());
		user2Recite.setPoemskuDetailId(commitrecitationParam.getPoemskuDetailId());
		User2Recite ur_ = user2ReciteMapper.findByUidAndCourseIdAndPoemskuDetailId(user2Recite);

		if(ur_ == null){
			user2Recite.setStatus(0);
//			user2Recite.setAudioFull(url);//第一次插入此表数据时不对audioFull进行赋值
			user2ReciteMapper.insert(user2Recite);
		}else{
			user2Recite.setId(ur_.getId());
		}

		if(commitrecitationParam.getAudioFull() != null && commitrecitationParam.getAudioFull() == 1){
			//最后完整的音频
			user2Recite.setAudioFull(url);
			user2ReciteMapper.update(user2Recite);
		}else{//分句音频
			User2ReciteData user2ReciteData = new User2ReciteData();
			user2ReciteData.setPoemskuDetailDataId(commitrecitationParam.getPoemskuDetailDataId());
			user2ReciteData.setUser2reciteId(user2Recite.getId());
			user2ReciteData.setCourseId(commitrecitationParam.getCourseId());
			user2ReciteData.setUserId(commitrecitationParam.getUid());
			User2ReciteData ud_ = user2ReciteMapper.findUser2ReciteData(user2ReciteData);
			if(ud_ != null){//之前已经提交过录音，此次更新
				ud_.setAudioUrl(url);
				user2ReciteMapper.updateUser2ReciteData(ud_);
			}else{
				user2ReciteData.setAudioUrl(url);
				user2ReciteData.setStatus(0);
				user2ReciteMapper.insertUser2ReciteData(user2ReciteData);
			}
		}
    	
    	//4  如果是最后一个 完整朗诵 ，更新 用户参与课程模块状态
		if(commitrecitationParam.getAudioFull() != null && commitrecitationParam.getAudioFull() == 1){
        	int result = user2CourseModuleMappper.updateUser2CourseModule(commitrecitationParam.getUid(), commitrecitationParam.getCourseId(), commitrecitationParam.getModuleId(), commitrecitationParam.getSkuId());
        }
        return TouchApiResponse.success();
    }

    /**
     * APP提交音频文件
     * @param commitrecitationParam
     * @return
     */
    @Override
    public ResponseEntity commitrecitationApp(CommitrecitationParam commitrecitationParam) {
        //1、解析APP端传过来的File
        //2、将资源上传至文件存储服务器
        //3、将信息插入user2recite
    	
    	
    	
    	
    	
    	
    	
    	//4  如果是最后一个 完整朗诵 ，更新 用户参与课程模块状态
        if(commitrecitationParam.getAudioFull()!=null)
        {
        	int result=user2CourseModuleMappper.updateUser2CourseModule(commitrecitationParam.getUid(), commitrecitationParam.getCourseId(), 3, commitrecitationParam.getSkuId());
        }	
        return TouchApiResponse.success();
    }
    
    
    

    /**
     * 古诗sku-古诗朗诵页获取试听录音
     * @param auditionrecitationParam
     * @return
     */
    @Override
    public ResponseEntity auditionrecitation(AuditionrecitationParam auditionrecitationParam) {

        List<Map> list = user2ReciteMapper.getAuditData(auditionrecitationParam.getUser2reciteId(), auditionrecitationParam.getUser2reciteDataId());
		List<PoemRecicationVo> poemRecicationVoList = new ArrayList<PoemRecicationVo>();
		PoemRecicationVo poemRecicationVo = null;
		for(Map map : list){
			poemRecicationVo = new PoemRecicationVo();
			TransferUtil.transferIgnoreNull(map, poemRecicationVo);
			poemRecicationVoList.add(poemRecicationVo);
		}

        return TouchApiResponse.success(poemRecicationVoList);
    }

    
    
    
    
    
    /**
     * 微信提交音频文件
     * @param commitReciteParam
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity commitreciteWx(CommitReciteParam commitReciteParam) {
		//1、通过微信openID、resourcesUrl 获取资源
		//2、将资源上传至文件存储服务器, 设置  commitrecitationParam recordingAdd 属性的值
		//3、调用音频转换接口获取分数值，将信息插入user2test_recite表

		//下载微信音频文件
		String wxFilePath = "";
		//上传至腾讯云存储服务器

		String returnUrl = "";
		//音频转换文本
//		String content = voice2WordsUtil.voice2Words(returnUrl);

		//获取原音频文本

		TestRecite testRecite = testReciteMapper.findById(commitReciteParam.getTestReciteId());
		//对比文本计算分数值
//		int score = StrCompareUtil.getCompareVal(testRecite.getAnswer(), content);

		int score = 0;

		User2TestRecite user2TestRecite = new User2TestRecite();
		user2TestRecite.setUserid(commitReciteParam.getUid());
		user2TestRecite.setCourseId(commitReciteParam.getCourseId());
		user2TestRecite.setTestReciteId(commitReciteParam.getTestReciteId());
		user2TestRecite.setRecordingAdd(commitReciteParam.getRecordingAdd());
		user2TestRecite.setTestScore(score);
		User2TestRecite user2TestRecite1 = user2TestReciteMapper.selectUser2TestRecite(user2TestRecite);
		if (null != user2TestRecite1) {
			//更新
			int result = user2TestReciteMapper.updateUser2TestReciteAudioFull(user2TestRecite);
			if (result == 0) {
				return TouchApiResponse.failed("更新失败");
			}
		} else {
			//新增
			user2TestRecite.setStatus(0);
			int result = user2TestReciteMapper.insertUser2TestRecite(user2TestRecite);
			if (result == 0) {
				return TouchApiResponse.failed("插入失败");
			}
		}
		return TouchApiResponse.success();
	}
    

    /**
     * APP提交音频文件
     * @param commitrecitationParam
     * @return
     */
    @Override
    public ResponseEntity commitreciteApp(CommitrecitationParam commitrecitationParam) {
        //1、解析APP端传过来的File
        //2、将资源上传至文件存储服务器 设置  commitrecitationParam recordingAdd 属性的值
    	//3、将信息插入user2recite, user2course表更新分数， 单课 与 周测和结课，测试的 分数逻辑不一样

		return null;
    	//1,2 todo
    	
    	//3
//    	ResponseEntity responseEntity=processTestRecite(commitrecitationParam);
//        return responseEntity;
    }

	/**
	 * 查询用户朗诵音频数据
	 * @param uid
	 * @param courseId
	 * @return
	 */
	@Override
	public User2ReciteVO findByParam(Integer uid, Integer courseId) {
		User2Recite user2Recite = user2ReciteMapper.findByParam(uid, courseId);
		User2ReciteVO vo = new User2ReciteVO();
		TransferUtil.transferIgnoreNull(user2Recite, vo);
		vo.setUser2ReciteId(user2Recite.getId());
		return vo;
	}
}
