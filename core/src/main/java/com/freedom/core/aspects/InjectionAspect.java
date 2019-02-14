package com.freedom.core.aspects;

import com.freedom.core.config.Constant;
import com.freedom.core.config.MyYml;
import com.freedom.core.jwt.JWTUtil;
import com.freedom.core.pojo.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@Order(4)
public class InjectionAspect {

    @Autowired
    private JWTUtil jwtUtil;

    @Pointcut("execution(public * com.freedom.*.service.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        if (!method.contains("add") && !method.contains("update") && !method.contains("delete")) {
            return;
        }
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求token中的人员信息
        String token = request.getHeader(Constant.TOKE_NNAME);

        jwtUtil.verify(token);
        String id = jwtUtil.getId(token);

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
                base.setCreateUser(new Long(id));
                base.setIsDelete(true);
                base.setStatus(true);
            } else if (method.contains("update")) {
                base.setUpdateTime(new Date());
                base.setUpdateUser(new Long(id));
            } else if (method.contains("delete")) {
                base.setIsDelete(false);
                base.setUpdateUser(new Long(id));
            }

        }
    }
}
