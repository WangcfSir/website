package com.website.system.controller;

import com.website.common.controller.BaseController;
import com.website.system.model.User;
import com.website.system.service.UserService;
import com.website.system.utils.EmailUtil;
import com.website.system.utils.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SignController extends BaseController {

    @Resource
    private UserService userService;

    private  SmsUtil smsUtil;

    private EmailUtil emailUtil;

    /**
     * 发送验证码
     */
    @ResponseBody
    @RequestMapping(value = "/sms",method = RequestMethod.GET)
    public Object sms(String phone) {
        Object code=smsUtil.mobileQuery(phone,"164488");
        return code;
    }

    /**
     * 注册成功通知
     */
    @ResponseBody
    @RequestMapping(value = "/smsAdvice",method = RequestMethod.GET)
    public Object smsAdvice(String phone) {
        Object code=smsUtil.mobileQuery(phone,"164564");
        return code;
    }

    /**
     * 发送邮箱真实性验证
     */
    @ResponseBody
    @RequestMapping(value = "/emailVerification",method = RequestMethod.GET)
    public Object emailVerification(String email) {
        Object code=emailUtil.Email(email);
        return code;
    }

    /**
     * 注册成功发送邮箱
     */
    @ResponseBody
    @RequestMapping(value = "/SendEmail",method = RequestMethod.GET)
    public Object SendEmail(String email) {
        Object code=emailUtil.SendEmail(email);
        return code;
    }

    /**
     * 跳转到 后台-->下载中心管理
     */
    @RequestMapping(value = "/sign",method = RequestMethod.GET)
    public String Sign() {
        return "/manage/sign";
    }

    /**
     * 保存新建用户
     */
    @ResponseBody
    @RequestMapping(value = "/signUserAdd",method = RequestMethod.POST)
    public String userAdd(@ModelAttribute User user) {
        try {
            user.setState(SHOW_STATE);
            user.setLevel(MANAGEMENT_LEVEL);
            user.setCreateTime(new Date());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(format.parse(user.getDate()));
            user.setDate(null);
            userService.insert(user);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }
}
