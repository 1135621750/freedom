package com.freedom.core.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 操作日志切面
 */
@Aspect
@Component
@Slf4j
@Order
public class OperationLog {
}
