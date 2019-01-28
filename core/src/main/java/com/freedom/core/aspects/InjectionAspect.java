package com.freedom.core.aspects;

import com.freedom.core.pojo.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 新增 修改时间用户等信息切面注入
 */
@Aspect
@Component
@Slf4j
@Order
public class InjectionAspect {

    @Pointcut("execution(public * com.freedom.*.service.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求token中的人员信息

        String method = joinPoint.getSignature().getName();

        Object[] obj = joinPoint.getArgs();
        for (Object o : obj) {
            if (!(o instanceof BaseModel)) {
                continue;
            }
            BaseModel base = (BaseModel) o;
            //是否是新增修改或删除方法
            if (!method.contains("add") && !method.contains("update") && !method.contains("delete")) {
                continue;
            }
            if (method.contains("add")) {
                base.setCreateTime(new Timestamp(System.currentTimeMillis()));
                base.setCreateUser(1L);
            } else if (method.contains("update")) {
                base.setUpdateTime(new Date());
                base.setUpdateUser(1L);
            } else if (method.contains("delete")) {
                base.setIsDelete(false);
                base.setUpdateUser(1L);
            }

        }
    }
}
