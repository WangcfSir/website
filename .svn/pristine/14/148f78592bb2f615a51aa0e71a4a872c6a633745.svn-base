package com.website.core.aop;

import com.website.common.exception.*;
import com.website.common.tips.ErrorTip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.website.core.support.HttpKit.getRequest;


/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * // ExceptionHandler()这里的异常是抛出来的
 *
 * @author yanqb
 * @date 2016年11月12日 下午3:19:56
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     *
     * @author yanqb
     */
    @ExceptionHandler(BussinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(BussinessException e) {
        getRequest().setAttribute("tip", e.getMessage());
        log.error("业务异常:", e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }

    /**
     * 用户未登录
     *
     * @author yanqb
     */
    @ExceptionHandler(IsLoginValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(IsLoginValidException e,Model model) {
        log.error("用户未登陆：", e);
        model.addAttribute("tips", "用户未登陆");
        return "manage/login";
    }

    /**
     * 用户登录session失效
     *
     * @author yanqb
     */
    @ExceptionHandler(InvalidSessionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String sessionTimeout(InvalidSessionException e, Model model) {
        log.error("登录session失效：", e);
        model.addAttribute("tips", "登录session失效，请重新登录");
        return "manage/login";
    }

    /**
     * 账号密码错误
     *
     * @author yanqb
     */
    @ExceptionHandler(LoginValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(LoginValidException e, Model model) {
        model.addAttribute("tips", "用户未激活或账号密码错误");
        return "manage/login";
    }

    /**
     * 验证码错误
     *
     * @author yanqb
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidKaptchaException e, Model model) {
        model.addAttribute("tips", "验证码错误");
        return "manage/login";
    }

    /**
     * 请求路径异常
     *
     * @author yanqb
     */
    @ExceptionHandler(UrlErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String urlError(Model model) {
        model.addAttribute("tips", "请求路径异常");
        return "404";
    }

    /**
     * 拦截未知的运行时异常
     *
     * @author yanqb
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        getRequest().setAttribute("tip", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new ErrorTip(BizExceptionEnum.SERVER_ERROR);
    }

    private void assertAjax(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有，x-requested-with
            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
        }
    }

}
