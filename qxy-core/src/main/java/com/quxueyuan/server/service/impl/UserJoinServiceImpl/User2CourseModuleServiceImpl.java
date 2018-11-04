package com.quxueyuan.server.service.impl.UserJoinServiceImpl;

import com.quxueyuan.bean.domain.CourseModule;
import com.quxueyuan.bean.domain.UserJoin.User2CourseModule;
import com.quxueyuan.bean.vo.req.CourseModuleParam;
import com.quxueyuan.bean.vo.res.TouchApiResponse;
import com.quxueyuan.server.api.service.CourseModuleService;
import com.quxueyuan.server.api.service.UserJoinService.User2CourseModuleService;
import com.quxueyuan.server.dao.User2CourseModuleMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class User2CourseModuleServiceImpl implements User2CourseModuleService {

    @Resource
    private User2CourseModuleMapper user2CourseModuleMapper;
    @Resource
    private CourseModuleService courseModuleService;

    /**
     * @description 根据用户id以及用户参与课程模块id进行查询
     * @param uid
     * @param skuId
     * @return
     */
    public User2CourseModule getUser2CourseModuleByModuleId(Integer uid, Integer moduleId, Integer courseId, Integer skuId){
        User2CourseModule user2CourseModule=user2CourseModuleMapper.selectUser2CourseModule(uid,moduleId,courseId,skuId);
        return user2CourseModule;
    }

    @Override
    public ResponseEntity unlockNextModule(Integer uid, Integer courseId, Integer moduleId, Integer skuId) {
        //1、修改当前模块的状态status字段
        int updateResult=user2CourseModuleMapper.updateUser2CourseModule(uid,courseId,moduleId,skuId);
        if(updateResult<1){
            return TouchApiResponse.failed("更新模块状态失败");
        }
        //2、查询coursemodule表 根据skuid按照sort排序
        List<CourseModule> courseModuleList=courseModuleService.getCourseModule(skuId);
        //3、list遍历结果获取下一条数据
        Integer nextModuleId=0;
        for(int i=0;i<courseModuleList.size();i++){
            if(moduleId==courseModuleList.get(i).getId()){
                //4、获取下一个course_module的id值
                nextModuleId=courseModuleList.get(i+1).getId();
            }
        }
        //5、增加数据库表user2coursemodule
        CourseModuleParam courseModuleParam=new CourseModuleParam();
        courseModuleParam.setUid(uid);
        courseModuleParam.setSkuId(skuId);
        courseModuleParam.setModuleId(nextModuleId);
        courseModuleParam.setCourseId(courseId);
        int result=user2CourseModuleMapper.insertUser2CourseModule(courseModuleParam);
        if(result>0){
            return TouchApiResponse.success("插入成功");
        }else {
            return TouchApiResponse.failed("用户参与下一个模块失败！！！");
        }
    }

    @Override
    public List<User2CourseModule> findUser2CourseModuleList(Integer uid, Integer skuId) {
        return user2CourseModuleMapper.selectUser2CourseModuleList(uid, skuId);
    }

    @Override
    public int updateUser2CourseModuleStatus(Integer userId, Integer courseId, Integer moduleid, Integer skuId) {
        return user2CourseModuleMapper.updateUser2CourseModule(userId,courseId,moduleid,skuId);
    }


}
