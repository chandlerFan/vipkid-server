package com.quxueyuan.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 *
 *
 * Description: 拦截controller
 *
 * @author liuwei
 * @version V1.0
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.quxueyuan.server.swagger..*.*(..))")
	private void jerseyLog(){}

    @Before(value="jerseyLog()")
	public void beforeJersey(JoinPoint point){
        log.info("进入Controller:{}, 入参:{}", Arrays.asList(point.getSignature()), Arrays.asList(point.getArgs()));
	}

    @AfterReturning(value="jerseyLog()", returning="val")
    public void afterJerseyException(JoinPoint point, Object val) throws Exception{
        log.info("进入Controller:{}, 返回结果:{}", Arrays.asList(point.getSignature()), val);
    }


    @AfterThrowing(throwing = "e",value="jerseyLog()")
    public void deleteBigMeeting_Throw(JoinPoint point, Throwable  e){
        log.info("进入Controller:{}, 异常:{}", Arrays.asList(point.getSignature()), e);
    }
}
