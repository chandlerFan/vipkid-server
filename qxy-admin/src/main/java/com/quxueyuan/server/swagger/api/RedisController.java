package com.quxueyuan.server.swagger.api;


import com.quxueyuan.bean.vo.res.ApiResponse;
import com.quxueyuan.bean.vo.res.ResponseModel;
import com.quxueyuan.server.api.service.RedisService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liuwei@juzifenqi.com
 * @Date 2018年06月28日 11:16
 * @Description: Redis操作接口
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    /**
     * 通过父类编号，查询所属下级城市
     *
     * @param key
     * @return
     */
    @ApiOperation(value = "通过Redis key 删除Reids数据")
    @PostMapping("/delByKey")
    @ResponseBody
    public ResponseEntity<ResponseModel> delByKey(String key) {
        try {
            redisService.deleteByKey(key);
            return ApiResponse.success("Success");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ApiResponse.failed(e.getMessage());
        }
    }

}
