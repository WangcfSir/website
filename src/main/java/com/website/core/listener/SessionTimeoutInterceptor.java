package com.website.core.listener;

import com.website.common.controller.BaseController;
import com.website.common.exception.InvalidSessionException;
import com.website.common.exception.IsLoginValidException;
import com.website.core.support.HttpKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import javax.websocket.SessionException;

/**
 * 验证session超时的拦截器 session-open目前未打开
 *
 * @author yanqb
 * @date 2018年11月19日21:08:48
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "website", name = "session-open", havingValue = "true")
public class SessionTimeoutInterceptor extends BaseController {

    @Pointcut("execution(* com.website.system.controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionTimeoutValidate(ProceedingJoinPoint point) throws Throwable {

        String servletPath = HttpKit.getRequest().getServletPath();

        if (servletPath.equals("/kaptcha") || servletPath.equals("/login") || servletPath.equals("/global/sessionError")) {
            return point.proceed();
        }else{
            if(getSession(false) == null){
                removeUser();
                throw new InvalidSessionException();
            }else{
                return point.proceed();
            }
        }
    }
}
