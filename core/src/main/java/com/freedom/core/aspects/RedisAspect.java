package com.freedom.core.aspects;

import com.freedom.core.config.MyYml;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * redis切面处理
 */
@Aspect
@Slf4j
@Configuration
public class RedisAspect {
    //是否开启redis缓存  true开启   false关闭
    @Autowired
    private MyYml yml;

    @Around("execution(* com.freedom.core.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(yml.getRedisOpen()){
            try{
                result = point.proceed();
            }catch (Exception e){
                log.error("redis error", e);
                throw new Exception("Redis服务异常");
            }
        }
        return result;
    }
}
