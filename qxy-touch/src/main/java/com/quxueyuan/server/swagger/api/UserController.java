package com.quxueyuan.server.swagger.api;


import com.quxueyuan.bean.domain.User;
import com.quxueyuan.bean.domain.UserLogin;
import com.quxueyuan.bean.vo.req.*;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.bean.vo.res.UserVO;
import com.quxueyuan.common.util.wx.WxUtil;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description 用户操作控制层
 */
@Slf4j
@RestController
public class UserController {

	@Resource
    private UserService userService;

	/**
	 * 获取用户信息
	 * @param uidParam
	 * @return
	 */
    @ResponseBody
    @PostMapping(value = "/user/getuserinfo")
    public ResponseEntity getuserinfo(@RequestBody UidParam uidParam) {
        String openId="";
        String accessToken="";
        try {
            Map<String, String> authMap = new WxUtil().getAccessTokenAuth(uidParam.getWxCode());
            openId=authMap.get("openid");
            accessToken=authMap.get("access_token");

            if("".equals(openId)){
                return TouchApiResponse.failed("未发现用户的openId");
            }
            //查询openId
            UserLogin userLogin=userService.getUserByOpenId(openId);
            if(null!=userLogin){
                //存在返回前端
                return TouchApiResponse.success(userLogin);
            }else{
                //获取数据
                JSONObject jsonObject =new WxUtil().getUserinfoAuth(accessToken,openId);
                //插入数据库user表以及user_login表
                return TouchApiResponse.success();
            }
        }catch(Exception e){
            e.printStackTrace();
            return TouchApiResponse.failed("微信服务器维护中。。。");
        }
    }

    public UserVO getUserVo(User user) {

        UserVO userVo=new UserVO();
        userVo.setUid(user.getId());
        userVo.setName(user.getName());
        userVo.setPic(user.getPic());
        userVo.setSex(user.getSex());
        return userVo;
    }

    /**
     * 更新用户信息
     * @return
     */
//    @ResponseBody
//    @PostMapping(value = "/user/updateuserinfo")
//    public Responses updateuserinfo(@RequestBody UserParam userParam) {
//
//    	Responses responses=userService.updateUser(userParam);
//    	return responses;
//    }

    @ResponseBody
    @PostMapping(value= "/user/adduserinfo")
    public ResponseEntity addUserInfo(@RequestBody UserParam userParam){
        int result=userService.addUser(userParam);
        if(result==1){
            return TouchApiResponse.success();
        }else{
            return  TouchApiResponse.failed();
        }
    }

    /**
     * 用户注册
     * @param userRegisterParam
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserRegisterParam userRegisterParam) {
        try {
            log.info("register - Param,{};", userRegisterParam.toString());
            return userService.register(userRegisterParam);
        } catch (TouchCodeException te) {
            log.error("请求返回异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("古诗sku-古诗朗诵页 提交录音 接口-APP:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

    /**
     * 用户登录
     * @param userLoginParam
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserLoginParam userLoginParam) {
        try {
            log.info("login - Param,{};", userLoginParam.toString());
            return userService.login(userLoginParam);
        } catch (TouchCodeException te) {
            log.error("请求返回异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("古诗sku-古诗朗诵页 提交录音 接口-APP:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

    /**
     * 微信授权globalAccessAuth
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "微信授权认证", notes = "微信授权认证")
    @RequestMapping(value = "/wx/auth", method = RequestMethod.POST)
    public ResponseEntity auth(@RequestBody WxAuthParam wxAuthParam) {
        try {
            log.info("auth - Param,{}", wxAuthParam);
            return userService.auth(wxAuthParam.getUrl());
        } catch (TouchCodeException te) {
            log.error("请求返回异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("古诗sku-古诗朗诵页 提交录音 接口-APP:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

    /**
     * 通过code获取openid
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "通过code获取openid", notes = "通过code获取openid")
    @RequestMapping(value = "/wx/getopenid", method = RequestMethod.POST)
    public ResponseEntity getopenid(@RequestBody GetOpenIdParam getOpenIdParam) {
        try {
            log.info("getopenid - Param,{}", getOpenIdParam);
            return userService.getopenid(getOpenIdParam);
        } catch (TouchCodeException te) {
            log.error("请求返回异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("古诗sku-古诗朗诵页 提交录音 接口-APP:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

}
