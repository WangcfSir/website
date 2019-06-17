package com.website.core.listener;

import com.website.common.controller.BaseController;
import com.website.common.exception.InvalidSessionException;
import com.website.common.exception.IsLoginValidException;
import com.website.common.exception.UrlErrorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 *  拦截注解@requestMapping，判断用户是否登录
 *
 * @author wangcf
 * @date 2019年06月12日 下午14:15:42
 */
@Aspect
@Component
public class RequestMappingInterceptor extends BaseController {

//    @Pointcut("execution(* com.website.system.controller.*.*(..))")

    @Around("@annotation(requestMapping)")
    public Object bindUserAdvice(ProceedingJoinPoint pjp, RequestMapping requestMapping) throws Throwable {

        Method method = ((MethodSignature)pjp.getSignature()).getMethod();
        String methodName = method.getName();
        // 网页page请求直接通过，错误页面属于路径请求有误，未登录其他访问
        if(methodName.contains("page")){
            return pjp.proceed();
        }
        if(methodName.equals("Sign")){
            return pjp.proceed();
        }
        if(methodName.equals("sms")){
            return pjp.proceed();
        }
        if(methodName.equals("userAdd")){
            return pjp.proceed();
        }
        if(methodName.equals("signUserAdd")){
            return pjp.proceed();
        }
        if(methodName.equals("smsAdvice")){
            return pjp.proceed();
        }
        if(methodName.equals("emailVerification")){
            return pjp.proceed();
        }
        if(methodName.equals("SendEmail")){
            return pjp.proceed();
        }
             /*System.out.print("[**请求名称："+methodName+"**]");*/
        if("errorHtml".equals(methodName)){
            throw new UrlErrorException();
        }
        if(getSession(false) == null) {
            removeUser();
            throw new InvalidSessionException();
        }

        if (!"login".equals(methodName) && !"error".equals(methodName) && !"loginValid".equals(methodName) && !"kaptcha".equals(methodName)){
            if(getCurrentUser() == null ){
                throw new IsLoginValidException();
            }
        }

        return pjp.proceed();
    }
}
