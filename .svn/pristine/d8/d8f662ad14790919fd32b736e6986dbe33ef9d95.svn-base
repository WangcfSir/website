package com.website.system.controller;

import com.website.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * website项目后台
 *
 * @author yanqb
 * @date 2018-11-16 15:43
 */
@Controller
@RequestMapping("/manage")
public class ManageController extends BaseController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("currentUser",getCurrentUser());
        return "manage/index";
    }

    @RequestMapping("/main")
    public String main() {
        return "manage/main";
    }

}
