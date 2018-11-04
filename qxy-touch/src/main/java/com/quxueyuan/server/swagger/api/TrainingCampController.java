package com.quxueyuan.server.swagger.api;

import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.bean.vo.res.TrainingCmapVO;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.TrainingCmapService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 训练营控制层
 */
@Slf4j
@RestController
public class TrainingCampController {
    @Resource
    public TrainingCmapService trainingCmapService;

    /**
     * @description 训练营列表
     * @author liruichen
     */

    @ApiOperation(value = "训练营列表", notes = "训练营列表")
    @ResponseBody
    @PostMapping("/base/gettraingingcamplist")
    public ResponseEntity listTrainingCmap(){
        try{
            List<TrainingCmapVO> trainingCmapVOList = trainingCmapService.listTrainingCmap();
            return TouchApiResponse.success(trainingCmapVOList);
        } catch (TouchCodeException te) {
            log.error("请求返回异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("古诗sku-周测和结果测试报告接口:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

}
