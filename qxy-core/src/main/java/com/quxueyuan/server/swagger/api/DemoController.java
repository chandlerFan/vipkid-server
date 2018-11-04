package com.quxueyuan.server.swagger.api;

import com.quxueyuan.server.api.IdemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: liuwei
 * @date: 2018/7/13 19:55
 * @version: 1.0
 */
@Controller
@ResponseBody
public class DemoController {
    @Autowired
    private IdemoService idemoService;

    @RequestMapping("/test")
    public String test(){
        idemoService.adminDemo();
        return "success";
    }
}
