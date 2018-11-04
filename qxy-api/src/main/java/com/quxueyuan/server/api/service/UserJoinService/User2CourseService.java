package com.quxueyuan.server.api.service.UserJoinService;


import com.quxueyuan.bean.domain.UserJoin.User2Course;
import com.quxueyuan.bean.vo.req.CourseModuleParam;
import com.quxueyuan.bean.vo.req.GeneralParam;
import com.quxueyuan.bean.vo.req.User2CourseControllerParam;
import org.springframework.http.ResponseEntity;

public interface User2CourseService {

    ResponseEntity selectPoemCoursePage(User2CourseControllerParam user2CourseControllerParam);

    ResponseEntity getReciteReport(GeneralParam generalParam);

    ResponseEntity updateStatus(GeneralParam generalParam);

    ResponseEntity updateCourseModule(CourseModuleParam courseModuleParam);

    int user2CourseCountTest(Integer uid, Integer trainingCampId, Integer levelId, Integer subLevelId, Integer type);

    User2Course getUser2Course(Integer uid, Integer courseId, Integer trainingCampId);

    ResponseEntity saveUser2Course(GeneralParam generalParam);

    ResponseEntity getWeekReport(GeneralParam generalParam);

    int updateUser2Course(User2Course user2Course);
}
