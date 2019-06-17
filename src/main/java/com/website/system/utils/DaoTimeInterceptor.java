package com.website.system.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by jzq1999 on 2017/4/19.
 * 通过aop拦截后执行具体操作
 */
@Aspect
@Component
public class DaoTimeInterceptor {

    private final static String POINT = "execution(* com.website.system.controller.*.*(..))";

    @Pointcut(POINT)
    public void recordLog(){}

    @Around("recordLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = pjp.proceed(pjp.getArgs());
        stopWatch.stop();

        long cost = stopWatch.getTotalTimeMillis();
        if(cost > 10) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

            System.out.println("----------- 执行" + methodName + "方法, 用时: " + cost + "ms -----------");
        }

        return obj;
    }

}
