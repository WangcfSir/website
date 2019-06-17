package com.website.core.listener;

import com.website.common.controller.BaseController;
import com.website.core.util.HttpSessionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *  利用session的拦截器，验证用户是否登录currentUser☆；也可以拦截注解@requestMapping，@Around("@annotation(requestMapping)")
 *
 * @author yanqb
 * @date 2016年11月13日 下午10:15:42
 */
@Aspect
@Component
public class SessionInterceptor extends BaseController {

//    @Around("@annotation(requestMapping)")
    @Pointcut("execution(* com.website.system.controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {

        HttpSessionHolder.put(super.getHttpServletRequest().getSession());
        try {
            return point.proceed();
        } finally {
            HttpSessionHolder.remove();
        }
    }
}
