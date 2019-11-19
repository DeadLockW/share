package com.share.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * description: LoggingConfig <br>
 * date: 2019/11/12 17:42 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("within(com.share.comment..*)")
    public void logsPointCut(){}

    @Before("logsPointCut()")
    public void paramLogs (JoinPoint joinPoint) throws Throwable{
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL："+request.getRequestURL());
        log.info("IP:"+request.getRemoteAddr());
        log.info("PORT:"+request.getServerPort());
        log.info("PATH:"+request.getServletPath());
        log.info("METHOD:"+request.getMethod());
        log.info("AGS:"+ Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "obj",pointcut = "logsPointCut()")
    public void resultLogs(Object obj) throws Throwable {
        log.info("请求完成");
        log.info(">>>>>>>>>>>>>>>>RESPONSE :"+ JSONObject.toJSONString(obj));
    }
}
