package com.quxueyuan.common.util.tencent.objectStorageUtils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

@Component
public class DownloadFileUtil {

    @Resource
    private TokenUtil tokenUtil;
    /**
     * @description 文件从对象存储服务器中进行下载
     * @param bucketName 容器的名称
     * @param objectName 对象的名称
     * @param pathName  下载的目标路径以及下载完之后修改的文件名称 ps:/Users/liruichen/Desktop/mydown.txt
     * @return
     */
    public Boolean downloadFile(String bucketName,String objectName,String pathName){
        COSClient cosclient=tokenUtil.downloadGetToken();

        // 设置bucket名
        //String bucketName = "container1-1257948725";
        //String objectName = "upload_single_demo.txt";
        //String pathName="/Users/liruichen/Desktop/mydown.txt";
        try {

            // 指定要下载到的本地路径
            File downFile = new File(pathName);
            // 指定要下载的文件所在的 bucket 和对象键
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectName);
            ObjectMetadata downObjectMeta = cosclient.getObject(getObjectRequest, downFile);
            String etag=downObjectMeta.getETag();
            if(!"".equals(etag)){
                return true;
            }
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            cosclient.shutdown();
        }
            return false;
    }
}
