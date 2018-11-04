package com.quxueyuan.server.swagger.api.UserJoinController;

import com.quxueyuan.bean.vo.req.CourseModuleParam;
import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.req.User2CourseControllerParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.exception.TouchCodeException;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description 用户选择课程
 */
@Slf4j
@RestController
public class User2CourseController {

    @Resource
    private User2CourseService user2CourseService;

    /**
     * @description 用户选择课程(古诗课程首选页，课程分四类type)
     * @return
     */
    @ResponseBody
    @PostMapping("/poem/getcoursedetail")
    public ResponseEntity getcoursedetail(@RequestBody User2CourseControllerParam user2CourseControllerParam){
        try {
            log.info("getcoursedetail - Param,{};", user2CourseControllerParam.toString());
            return user2CourseService.selectPoemCoursePage(user2CourseControllerParam);
        } catch (TouchCodeException te) {
            log.error("请求异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新用户参与课程状态:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

    /**
     * @description 更新用户参与课程状态 单课、周测、结课测试的完成时更改状态
     * @return
     */
    @ApiOperation(value = "更新用户参与课程状态", notes = "更新用户参与课程状态")
    @ResponseBody
    @PostMapping("/user2course/updatestatus")
    public ResponseEntity updateStatus(@RequestBody GeneralParam generalParam){
        try {
            log.info("updateStatus - Param,{};", generalParam.toString());
            return user2CourseService.updateStatus(generalParam);
        } catch (TouchCodeException te) {
            log.error("请求异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新用户参与课程状态:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }

    /**
     * @description 前端在单课可解锁状态点击时需要插入用户课程数据
     * @return
     */
    @ApiOperation(value = "单课可解锁状态插入用户课程数据", notes = "单课可解锁状态插入用户课程数据")
    @ResponseBody
    @PostMapping("/user2course/saveUser2Course")
    public ResponseEntity saveUser2Course(@RequestBody GeneralParam generalParam){
        try {
            log.info("saveUser2Course - Param,{};", generalParam.toString());
            return user2CourseService.saveUser2Course(generalParam);
        } catch (TouchCodeException te) {
            log.error("请求异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("单课可解锁状态插入用户课程数据:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }


    /**
     * 更新 用户参与课程模块状态变更 接口
     */
    @ApiOperation(value = "用户参与课程模块状态变更", notes = "用户参与课程模块状态变更")
    @ResponseBody
    @PostMapping("/user/updatecoursemodule")
    public ResponseEntity updateCourseModule(@RequestBody CourseModuleParam courseModuleParam){
        try {
            log.info("CourseModuleParam - Param,{};", courseModuleParam.toString());
            return user2CourseService.updateCourseModule(courseModuleParam);
        } catch (TouchCodeException te) {
            log.error("请求异常:{}", te.getMessage());
            return TouchApiResponse.failed(te.getTouchApiCode().getCode(), te.getTouchApiCode().getMsg() + te.getExtendMsg());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户参与课程模块状态变更:{}", e.getMessage());
            return TouchApiResponse.failed(e.getMessage());
        }
    }
}
