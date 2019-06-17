package com.website.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.website.common.controller.BaseController;
import com.website.common.warpper.ResultMap;
import com.website.system.model.Doctor;
import com.website.system.model.DoctorCategory;
import com.website.system.service.DoctorCategoryService;
import com.website.system.service.DoctorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("manage/doctorCategory")
public class DoctorCategoryController extends BaseController {

    @Resource
    private DoctorCategoryService doctorCategoryService;

    @Resource
    private DoctorService doctorService;

    /**
     * 跳转到 后台-->下载中心管理
     */
    @RequestMapping("")
    public String doctor() {
        return "manage/doctorCategory";
    }

    /**
     * 加载数据
     */
    @ResponseBody
    @RequestMapping("/doctorCategoryTable")
    public ResultMap doctorCategoryTable(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam(required =false) String content) {
        ResultMap<List<DoctorCategory>> resultMap = new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, null);
        Page<DoctorCategory> pageInfo = new Page<>(page,limit);
        Wrapper<DoctorCategory> wrapper = new EntityWrapper<>();
        wrapper = content == null ? wrapper : wrapper.like("type", content);
        List<DoctorCategory> dcgList;
        if(content==null){
            dcgList = doctorCategoryService.selectPage(pageInfo,wrapper.orderBy("id", true)).getRecords();
        }else{
            dcgList = doctorCategoryService.selectList(wrapper);
        }
        resultMap.setCount(doctorCategoryService.selectCount(wrapper));
        resultMap.setData(dcgList);
        return resultMap;
    }

    /**
     *  加载医生类别
     */
    @ResponseBody
    @RequestMapping(value = "/SelDoctorCategory",method = RequestMethod.POST)
    public List<DoctorCategory> SelDoctorCategory() {
        Wrapper<DoctorCategory> w = new EntityWrapper<>();
        List<DoctorCategory> list=doctorCategoryService.selectList(w);
        return list;
    }

    /**
     *  查询医生类别
     */
    @ResponseBody
    @RequestMapping(value = "/SelTypeById",method = RequestMethod.POST)
    public Object SelTypeById(Integer id) {
        DoctorCategory doctorCategory=doctorCategoryService.selectById(id);
        return doctorCategory;
    }

    /**
     * 添加医生类
     */
    @RequestMapping("/doctorCategoryAdd")
    public String doctorAdd() {
        return "manage/doctorCategoryAdd";
    }

    /**
     * 保存新建医生类
     */
    @ResponseBody
    @RequestMapping(value = "/toDoctorCategoryAdd",method = RequestMethod.POST)
    public String articleAdd(@ModelAttribute DoctorCategory doctorCategory) {
        try {
            doctorCategoryService.insert(doctorCategory);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 修改页面跳转
     */
    @RequestMapping("/selDoctorCategory/{id}")
    public String getArticle(Model model, @PathVariable Integer id) {
        DoctorCategory doctorCategory = doctorCategoryService.selectById(id);
        doctorCategory.setType(doctorCategory.getType().replaceAll("\"","&quot;"));
        model.addAttribute("doctorCategory", doctorCategory);
        return "manage/doctorCategoryUpdate";
    }

    /**
     * 修改保存
     */
    @ResponseBody
    @RequestMapping("/doctorCategoryUpdate")
    public String articleUpdate(@ModelAttribute DoctorCategory doctorCategory ) {
        try{
            doctorCategoryService.updateById(doctorCategory);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     *  删除上层判断
     */
    @ResponseBody
    @RequestMapping(value = "/SelDocById",method = RequestMethod.POST)
    public Object SelDocById(String id) {
        Wrapper<Doctor> wrapper = new EntityWrapper<>();
        wrapper = id == null ? wrapper : wrapper.eq("doctorcategoryid", id);
        int i=doctorService.selectCount(wrapper);
        return i;
    }

    /**
     * 删除（下载中心--->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDoctorCategory",method = RequestMethod.POST)
    public String deleteDoctor(@Param("id") Integer id) {
        try{
            doctorCategoryService.deleteById(id);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }
}
