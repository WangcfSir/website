package com.website.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全局的控制器
 *
 * @author yanqb
 * @date 2018年11月13日 下午11:04:45
 */
@Controller
@RequestMapping("/global")
public class GlobalController {

    /**
     * 跳转到404页面
     *
     * @author yanqb
     */
    @RequestMapping(path = "/error")
    public String errorPage() {
        return "/404";
    }

    /**
     * 跳转到session超时页面
     *
     * @author yanqb
     */
    @RequestMapping(path = "/sessionError")
    public String errorPageInfo(Model model) {
        model.addAttribute("tips", "session超时");
        return "/login";
    }
}
