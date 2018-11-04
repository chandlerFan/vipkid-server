package com.quxueyuan.common.util.tencent.objectStorageUtils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TokenUtil {


    @Value("${tencent.appid}")
    private   String APPID;

    @Value("${tencent.secretid}")
    private  String SECRETID;

    @Value("${tencent.secretkey}")
    private  String SECRETKEY;

    @Value("${tencent.regionName}")
    private  String REGION_NAME;


    public  COSClient uploadGetToken(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(SECRETID, SECRETKEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(REGION_NAME));
        // 3 生成cos客户端
         return  new COSClient(cred, clientConfig);
    }

    public  COSClient downloadGetToken(){
        // 1 初始化用户身份信息(appid, secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(APPID, SECRETID, SECRETKEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(REGION_NAME));
        // 3 生成cos客户端
        return new COSClient(cred, clientConfig);
    }
}
