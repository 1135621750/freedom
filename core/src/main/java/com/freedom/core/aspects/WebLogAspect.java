package com.freedom.core.aspects;

import com.alibaba.fastjson.JSONObject;
import com.freedom.core.pojo.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 请求日志
 *
 * @author Bai
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class WebLogAspect {

    @Pointcut("execution(public * com.freedom.*.web.*.*(..))")
    public void webLog() {
    }

    /**
     * 前置
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("-------------------请求开始-----------------");
        log.info("请求头token : " + request.getHeader(""));
        log.info("请求路径是 : " + request.getRequestURL().toString());
        log.info("请求方式是 : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("请求方法全路径 : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("参数 : ");
        if ("application/json".equals(request.getHeader("Content-Type"))) {
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Object[] objects = joinPoint.getArgs();
            for (int i = 0; i < method.getParameters().length; i++) {
                Parameter parameter = method.getParameters()[i];
                if (parameter.isAnnotationPresent(RequestBody.class) || parameter.isAnnotationPresent(ModelAttribute
                        .class)) {
                    //记录application/json时的传参，SpringMVC中使用@RequestBody接收的值
                    log.info(objects[i].toString());
                }
            }

        } else {
            //记录请求的键值对
            for (String key : request.getParameterMap().keySet()) {
                log.info(key + " --> " + request.getParameter(key));
            }
        }

    }

    /**
     * 返回值校验
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("方法的返回值 : " + JSONObject.toJSONString(ret));
        log.info("==================请求结束===================");
    }

    /**
     * 计算请求时间
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        log.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}
