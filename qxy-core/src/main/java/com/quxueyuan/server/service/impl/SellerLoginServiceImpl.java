package com.quxueyuan.server.service.impl;

import com.quxueyuan.bean.vo.req.SellerLoginReq;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.*;
import com.quxueyuan.server.service.SystemLogSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Title: SellerLoginServiceImpl
 * @Company: 北京桔子分期电子商务有限公司
 * @Author Li Zhe lizhe@juzifenqi.com
 * @Date 2018年06月29日 18:28
 * @Description: 商户登录操作接口实现
 */
@Slf4j
@Service
public class SellerLoginServiceImpl implements SellerLoginService {

//    @Value("${jwt.seller.ttlMillis}")
//    private int JWT_TTLMILLIS;
//
//    @Value("${jwt.seller.sec}")
//    private String JWT_SEC;
//
//    @Value("${spring.redis.timeout}")
//    private int REDIS_TIMEOUT;

//    @Value("${touch.test.login}")
//    private String testLogin;
//
//    @Value("${touch.test.mobile}")
//    private String testMobile;
//
//    @Value("${touch.test.password}")
//    private String testPass;

//    @Autowired
//    private RedisService redisService;

    @Autowired
    private SystemLogSupport systemLogSupport;

    /**
     * 商户端小程序登录验证
     * 1、验证手机号、商户店铺编码、密码是否正确
     * 2、验证商户是否冻结状态
     * 3、拿wxCode到微信端换取openID
     * 4、保存openID至seller_login_openID_rel   商户店员登录openID关联表
     * 5、以手机号、商户店铺编码、openID 为key生成token
     * 6、以openID为key ，手机号、商户店铺编码、openID 为value缓存至Redis
     * 7、将token返回给前端  sellerToken
     * <p>
     * sellerCode   BD后台维护的店铺编码
     * sellerMobile BD后台维护的店铺手机号
     * password     BD后台维护的店铺密码
     * wxCode       微信code
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> login(SellerLoginReq req) throws TouchCodeException {

        //插入日志
        return null;

    }

    //商户登录次数字典code值
    private final static String SELLER_LOGIN_COUNT_DICT = "SELLER_LOGIN_COUNT";

    @Override
    public boolean loginHandle(String openId, String sellerLogin, boolean loginStatus) {

        return false;
    }
}
