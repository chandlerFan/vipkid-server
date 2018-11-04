package com.quxueyuan.common.util.tencent.objectStorageUtils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;


@Component
public class UploadFileUtil {

    @Resource
    private TokenUtil tokenUtil;
    /**
     * @description 文件上传方法
     * @param bucketName 容器名称
     * @param pathName 上传文件路径
     * @param objectName 上传到容器的对象名称
     * @return
     */
    public  Boolean UploadFile(String bucketName, String pathName, String objectName){
        COSClient cosClient = tokenUtil.uploadGetToken();
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = new File(pathName);
        // 指定要上传到 COS 上对象键
        // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
        //String key = "upload_single_demo.txt";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, localFile);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
            putObjectRequest.setStorageClass(StorageClass.Standard_IA);
            try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            String etag = putObjectResult.getETag();
            if(!"".equals(etag)){
                cosClient.shutdown();
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
                // 关闭客户端
                cosClient.shutdown();
            }
            return false;
    }


}
