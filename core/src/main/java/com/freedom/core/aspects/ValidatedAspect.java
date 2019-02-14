package com.freedom.core.aspects;

import com.freedom.core.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 表单验证界面
 */
@Aspect
@Component
@Slf4j
@Order(3)
public class ValidatedAspect {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator methodValidator = factory.getValidator().forExecutables();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return methodValidator.validateParameters(obj, method, params);
    }

    @Pointcut("execution(public * com.freedom.*.web.*.*(..))")
    public void aspect() {
    }

    @Around("aspect()")
    public Object validate(ProceedingJoinPoint point) throws Throwable{
        //没有参数不验证
        Object[] args = point.getArgs();
        if (args.length == 0) {
            return point.proceed();
        }


        /*--------------单参验证----------------*/
        //  获得切入目标对象
        Object target = point.getThis();
        // 获得切入的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        // 校验以基本数据类型 为方法参数的
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);
        Iterator<ConstraintViolation<Object>> violationIterator = validResult.iterator();
        while (violationIterator.hasNext()) {
            violationIterator.next().getMessage();
            log.info("参数校验不通过-->" + violationIterator.next().getMessage());
            return JsonResult.error(violationIterator.next().getMessage());
        }


        /*-----------校验以java bean对象 为方法参数的--------------*/
        BindingResult bindingResult = null;
        for(Object arg : point.getArgs()){//遍历被通知方法(controller方法)的参数列表
            if(arg instanceof BindingResult){//参数校验结果会存放在BindingResult中
                bindingResult = (BindingResult) arg;
            }
        }
        if(bindingResult != null){
            if(bindingResult.hasErrors()){//检查是否存在校验错误
                List<FieldError> errors = bindingResult.getFieldErrors();//获取字段参数不合法的错误集合
                for(FieldError error : errors){
                    log.info("对象参数校验不通过-->" + error.getField() + ":" + error.getDefaultMessage());
                    return JsonResult.error(error.getDefaultMessage());
                }
            }
        }
        return point.proceed();
    }
}
