package com.quxueyuan.server.swagger.api.UserJoinController;

import com.quxueyuan.bean.vo.req.UserJoinTrainingCmapParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.UserJoinService.User2JoinTrainingCampService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description 用户参与训练营
 * @author liruichen
 */
@Slf4j
@RestController
public class User2JoinTrainingCampController {

    @Resource
    private User2JoinTrainingCampService user2JoinTrainingCampService;
    /**
     * @description 用户参与某个训练营接口
     * @param userJoinTrainingCmapParam 前台传入的参数对象
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "用户参与训练营入口", notes = "用户参与训练营入口")
    @PostMapping("/base/userjointrainingcamp")
    public ResponseEntity userJoinTrainingCamp(@RequestBody UserJoinTrainingCmapParam userJoinTrainingCmapParam){
        try {
            log.info("userJoinTrainingCamp - Param,{};", userJoinTrainingCmapParam.toString());
            return user2JoinTrainingCampService.userJoinTrainingCamp(userJoinTrainingCmapParam);
        } catch (TouchCodeException te) {
            log.error("请求返回异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("古诗训练营课程首页接口:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

}
