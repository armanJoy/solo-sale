package com.test.inventory.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ControllerAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.test.inventory.controller.*.*(..))")
    public Object controllerMethodExceptionHandlingAndLoging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        logger.info("{} started with parameters: {}", methodName, obtainParameters(joinPoint));
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            stopWatch.stop();
            logger.info("{} failed with exception message: {}", methodName, e.getMessage());
            throw e;
        }
        stopWatch.stop();
        logger.info("{} finished with return value: {} \t  executed in {} ms", methodName, proceed, stopWatch.getTotalTimeMillis());
        return proceed;
    }

//    @After("execution(* com.test.inventory.controller..*.*(..))")
//    public void logAfter(ProceedingJoinPoint joinPoint) {
//        logger.info("Method execution completed: {}", joinPoint.getSignature());
//    }

    private Map<String, Object> obtainParameters(ProceedingJoinPoint joinPoint) {
        Map<String, Object> parameters = new HashMap<>();
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length && i < parameterValues.length; i++) {
                parameters.put(parameterNames[i], parameterValues[i]);
            }
        }
        return parameters;
    }
}