package com.quxueyuan.server.api.service.UserJoinService;

import com.quxueyuan.bean.domain.UserJoin.User2CourseModule;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface User2CourseModuleService {

    User2CourseModule getUser2CourseModuleByModuleId(Integer uid, Integer moduleId, Integer courseId, Integer skuId);

    ResponseEntity unlockNextModule(Integer uid, Integer courseId, Integer moduleId, Integer skuId);

    List<User2CourseModule> findUser2CourseModuleList(Integer uid, Integer skuId);

    int updateUser2CourseModuleStatus(Integer userId, Integer courseId, Integer moduleid, Integer skuId);
}
