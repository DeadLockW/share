package com.share.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

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

    @Pointcut("within(com.share.comment.RouteExecutorService)")
    public void logsPointCut(){}

    @Before("logsPointCut()")
    public void paramLogs (JoinPoint joinPoint) throws Throwable{
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		/*
		 * HttpServletRequest request = attributes.getRequest();
		 * log.info("URL："+request.getRequestURL());
		 * log.info("IP:"+request.getRemoteAddr());
		 * log.info("PORT:"+request.getServerPort());
		 * log.info("PATH:"+request.getServletPath());
		 * log.info("METHOD:"+request.getMethod());
		 */
        log.info("请求参数：:"+ Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "obj",pointcut = "logsPointCut()")
    public void resultLogs(Object obj) throws Throwable {
        log.info("请求完成");
        log.info("响应结果 :"+ JSONObject.toJSONString(obj));
    }
}
