package com.freedom.core.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 请求日志切面
 */
@Aspect
@Component
@Slf4j
@Order(3)
public class RequestLog {


    /**
     * 定义aop切点
     */
    @Pointcut("@annotation(com.freedom.*.web.aspects.annotation.Log)")
    public void logPointCut() {
    }


}
