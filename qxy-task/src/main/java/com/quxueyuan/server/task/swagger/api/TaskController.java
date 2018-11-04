package com.quxueyuan.server.task.swagger.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class TaskController {


//    @Autowired
//    private LoginService loginService;

    @ApiOperation(value = "小程序商户端登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    String toLogin() {
        System.out.println("1111111111111111111111111111111111111111111");
        return "login";
    }

    @ApiOperation(value = "小程序商户端登录")
    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    String toRegister() {
        return "register";
    }


}
