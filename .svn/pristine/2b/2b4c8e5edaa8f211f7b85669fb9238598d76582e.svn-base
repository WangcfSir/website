package com.website.system.controller;

import com.google.code.kaptcha.Constants;
import com.website.common.controller.BaseController;
import com.website.common.exception.InvalidKaptchaException;
import com.website.common.exception.LoginValidException;
import com.website.core.util.ToolUtil;
import com.website.system.dao.UserMapper;
import com.website.system.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 管理员登录
 *
 * @author yanqb
 * @Date 2018-11-18 下午1:08:17
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    UserMapper userMapper;

    /**
     * 跳转到前台主页
     */
    @RequestMapping(value = {"","/index"}, method = RequestMethod.GET)
    public String pageIndex() {
        return REDIRECT + "/page/index";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = {"/login","manage/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        if (getCurrentUser() != null) {
            model.addAttribute("currentUser",getCurrentUser());
            return "manage/index";
        } else {
            return "manage/login";
        }
    }

    /**
     * 点击登录执行的动作 TODO 1.404页面优化，2.项目启动，第一次访问页面 没有解析${pageContext.request.contextPath} $%7B  %7D
     */
    @RequestMapping(value = "/loginValid", method = RequestMethod.POST)
    public String loginValid() {

        String username = super.getPara("userName").trim();
        String password = super.getPara("password").trim();

        //验证验证码是否正确
        if(ToolUtil.getKaptchaOnOff()){
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(ToolUtil.isEmpty(kaptcha) || !kaptcha.equals(code)){
                throw new InvalidKaptchaException();
            }
        }

        // 取数据库验证用户名和密码，然后保存当前用户到session
        User loginUser = userMapper.loginValid(username,password);
        if (loginUser == null) {
            throw new LoginValidException();
        }
        getSession().setAttribute("sessionFlag",true);
        getSession().setAttribute("currentUser",loginUser);

        return REDIRECT + "manage/index";
    }

    /**
     * 退出登录,切换账号
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        removeUser();
        return "/manage/login";
    }

}
