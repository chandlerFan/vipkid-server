package com.quxueyuan.server.service.impl;


import com.quxueyuan.bean.domain.User;
import com.quxueyuan.bean.domain.UserLogin;
import com.quxueyuan.bean.vo.req.GetOpenIdParam;
import com.quxueyuan.bean.vo.req.UserLoginParam;
import com.quxueyuan.bean.vo.req.UserParam;
import com.quxueyuan.bean.vo.req.UserRegisterParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.bean.vo.res.UserLoginVO;
import com.quxueyuan.common.enmu.TouchApiCode;
import com.quxueyuan.common.enmu.UserStatusCode;
import com.quxueyuan.common.util.TransferUtil;
import com.quxueyuan.common.util.wx.WxUtil;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.UserService;
import com.quxueyuan.server.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @descrption 用户操作业务层
 * @author liruichen
 *
 * 课程信息、用户信息、等级信息、训练营信息、用户参与课程信息
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	  @Resource
	    private UserMapper userMapper;

	 @Override
	    public User getUserById(Integer id)
	    {
	    	User user=userMapper.getUserById(id);

	    	return user;

	    }
	    
//	    @Override
//	    public Responses updateUser(UserParam userParam)
//	    {
//	    	User user=new User();
//	    	user.setId(userParam.getUid());
//	    	user.setName(userParam.getName());
//	    	user.setPic(userParam.getPic());
//	    	user.setSex(userParam.getSex());
//	    	int result= userMapper.updateUser(user);
//	    	System.out.println("result:"+result);
//	    	if(result==1)
//	    	{
//	    		return Utility.getRightResponse(new Object());
//	    	}
//	    	else {
//	    		return Utility.getErrorResponse(1, "更新数据失败");
//	    	}
//
//	    }

	@Override
	public int addUser(UserParam userParam) {
	 	User user=new User();
	 	user.setName(userParam.getName());
	 	user.setSex(userParam.getSex());
	 	user.setPic(userParam.getPic());
		int result=userMapper.insertUser(user);
		return result;
	}

	/**
	 * 用户注册
	 * @param userRegisterParam
	 * @return
	 */
	@Transactional
	@Override
	public ResponseEntity register(UserRegisterParam userRegisterParam) {
		//现根据phone判断是否已注册
		UserLogin temp = userMapper.findUserLoginByPhone(userRegisterParam.getPhone());
		if(temp != null){
			throw new TouchCodeException(TouchApiCode.USER_ALREADY_REGISTER);
		}

		//插入user_login  -- 此表初衷是用户注册表
		UserLogin userLogin = new UserLogin();
		TransferUtil.transferIgnoreNull(userRegisterParam, userLogin);
		userLogin.setStatus(UserStatusCode.STATUS_0.getCode());
		userMapper.insertUserLogin(userLogin);

		//插入user
		User user = new User();
		TransferUtil.transferIgnoreNull(userRegisterParam, user);
		user.setStatus(UserStatusCode.STATUS_0.getCode());
		user.setUid(userLogin.getId());
		userMapper.insertRegisterUser(user);

		return TouchApiResponse.success();
	}

	/**
	 * 用户登陆
	 * @param userLoginParam
	 * @return
	 */
	@Override
	public ResponseEntity login(UserLoginParam userLoginParam) {
		UserLogin userLogin = new UserLogin();
		TransferUtil.transferIgnoreNull(userLoginParam, userLogin);
		UserLogin u_login = userMapper.findUserLogin(userLogin);

		User user = userMapper.findUserByUid(u_login.getId());

		UserLoginVO userVo = new UserLoginVO();

		userVo.setPhone(u_login.getPhone());
		userVo.setStatus(u_login.getStatus());
		userVo.setOpenId(u_login.getOpenId());
		userVo.setSource(u_login.getSource());
		userVo.setUid(user.getUid());
		userVo.setName(user.getName());
		userVo.setSex(user.getSex());
		userVo.setPic(user.getPic());

		return TouchApiResponse.success(userVo);
	}

	@Autowired
	private WxUtil wxUtil;

	/**
	 * 微信授权
	 * @return
	 */
	@Override
	public ResponseEntity auth(String url) throws Exception {
//		String queryString = request.getQueryString();
//		log.info("微信认证请求queryString:{}", queryString);
//
//		String url = request.getRequestURL().append("?"+queryString).toString();
		log.info("微信认证请求url:{}", url);
		return TouchApiResponse.success(wxUtil.globalAccessAuth(url));
	}

	@Override
	public ResponseEntity getopenid(GetOpenIdParam getOpenIdParam) throws Exception {
		Map<String, String> map = wxUtil.getAccessTokenAuth(getOpenIdParam.getCode());
		return TouchApiResponse.success(map);
	}

	/**
	 * @description 根据openid进行查询用户信息
	 * @param openid
	 * @return
	 */
	@Override
	public UserLogin getUserByOpenId(String openid) {
		UserLogin userLogin=userMapper.selectUserByOpenid(openid);
		return null;
	}


}
