package com.website.system.controller;


import com.website.common.controller.BaseController;
import com.website.common.warpper.ResultMap;
import com.website.system.model.Association;
import com.website.system.service.AssociationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 文章控制器（核心）
 *
 * @author yanqb
 * @since 2018-11-23
 */
@Controller
@RequestMapping("manage/association")
public class AssociationController extends BaseController {

    @Resource
    private AssociationService associationService;

    /**
     * 跳转到 协会信息（简介、功能、联系）// ID和TYPE一致
     */
    @RequestMapping("")
    public String association() { return "manage/association"; }

    /**
     * 加载数据 协会信息
     */
    @ResponseBody
    @RequestMapping("/associationTable")
    public ResultMap associationTable() {
        ResultMap<List<Association>> resultMap = new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, null);
        resultMap.setData(associationService.selectList(null));
        return resultMap;
    }

    /**
     * 查看页面 协会信息
     */
    @RequestMapping("/toAssociationRead/{type}")
    public String associationRead(Model model, @PathVariable Integer type) {
        model.addAttribute("association", associationService.selectById(type));
        return "manage/associationRead";
    }

    /**
     * 修改页面 协会信息
     */
    @RequestMapping("/toAssociationUpdate/{type}")
    public String getAssociation(Model model, @PathVariable Integer type) {
        model.addAttribute("association", associationService.selectById(type));
        return "manage/associationUpdate";
    }

    /**
     * 保存修改 协会信息
     */
    @ResponseBody
    @RequestMapping("/associationUpdate")
    public String associationUpdate(@RequestParam Integer id, @RequestParam String content) {
        try{
            Association association = associationService.selectById(id);
            association.setContent(content);
            association.setModifyTime(new Date());
            associationService.updateById(association);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }
}
