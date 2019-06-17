package com.website.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.website.common.controller.BaseController;
import com.website.common.warpper.ResultMap;
import com.website.core.util.StringUtil;
import com.website.system.model.ArticleEasy;
import com.website.system.model.Article;
import com.website.system.model.Picture;
import com.website.system.service.ArticleService;
import com.website.system.service.PictureService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 暂时针对首页的轮播图和无线滚动图 前端控制器
 *
 * @author yanqb
 * @since 2018-11-23
 */
@Controller
@RequestMapping("manage/home")
public class HomeController extends BaseController {

    @Resource
    private PictureService pictureService;

    @Resource
    private ArticleService articleService;

    /**
     * 跳转到 后台-->主页管理
     */
    @RequestMapping("")
    public String homeList(Model model) {
        // 获取文章ID和标题 （供图片添加使用）,把剩下的顺序，供管理员选择；
        List<ArticleEasy> articleList = articleService.getArticleEasyForPicture();
        JSONArray articleArray = new JSONArray(Collections.singletonList(articleList));
        model.addAttribute("articleArray", articleArray);
        return "manage/home";
    }

    /**
     * 加载数据 后台-->主页管理
     */
    @ApiOperation("主页JSON数据")
    @ResponseBody
    @RequestMapping("/homeTable")
    public ResultMap homeTable(@RequestParam Integer page, @RequestParam Integer limit) {
        ResultMap<List<Picture>> resultMap = new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, null);
//
//        PageInfo pageInfo = new PageInfo<>(page,limit);
//        List<Picture> homeList = pictureService.selectPageList(null);
//        List<Picture> homeList = pictureService.selectPageCount(null);

        Wrapper<Picture> wrapper = new EntityWrapper<>();
        Page<Picture> pageInfo = new Page<>(page,limit);
        List<Picture> homeList = pictureService.selectPage(pageInfo, wrapper.orderBy("upload_time", false)).getRecords();
        resultMap.setCount(pictureService.selectCount(wrapper));
        resultMap.setData(homeList);
        return resultMap;
    }

    /**
     * 跳转到 后台-->主页管理--> 添加
     */
    @RequestMapping("/toHomeAdd")
    public String toHomeAdd(Model model) {
        model.addAttribute("optionalTopSort",StringUtil.getOptionalSort(pictureService.getSortArray(PICTURE_TYPE_TOP)));
        model.addAttribute("optionalDownSort",StringUtil.getOptionalSort(pictureService.getSortArray(PICTURE_TYPE_DOWN)));
        return "manage/homeAdd";
    }

    /**
     * 提交 主页内容添加
     */
    @ResponseBody
    @RequestMapping(value = "/homeAdd",method = RequestMethod.POST)
    public String homeAdd(@ModelAttribute Picture picture) {
        try {
            picture.setModularName("协会首页");
            picture.setPictureUrl("files/image/" + picture.getPictureId() + ".jpg");
            picture.setState(SHOW_STATE);
            picture.setUploadTime(new Date());
            Article article = articleService.selectById(picture.getArticleId());
            if(null != article){
                picture.setArticleTitle(article.getTitle());
            }
            pictureService.insert(picture);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 跳转到 主页管理--> 修改
     */
    @RequestMapping("/toHomeUpdate")
    public String toHomeUpdate(Model model) {
        model.addAttribute("optionalTopSort",StringUtil.getOptionalSort(pictureService.getSortArray(PICTURE_TYPE_TOP)));
        model.addAttribute("optionalDownSort",StringUtil.getOptionalSort(pictureService.getSortArray(PICTURE_TYPE_DOWN)));
        return "manage/homeUpdate";
    }

    /**
     * 提交 主页内容修改
     */
    @ResponseBody
    @RequestMapping(value = "/homeUpdate",method = RequestMethod.POST)
    public String pictureUpdate(@ModelAttribute Picture picture)  {
        try {
            Picture oldPicture = pictureService.selectById(picture.getId());
            // 判断图片的id有没有变动；变动的话，就删除原来的图片
            if(!oldPicture.getPictureId().equals(picture.getPictureId())){
                deleteFile(oldPicture.getPictureUrl());
                picture.setPictureUrl("files/image/" + picture.getPictureId() + ".jpg");
            }

            picture.setModifyTime(new Date());
            if(picture.getType().equals(PICTURE_TYPE_TOP)){
                Article article = articleService.selectById(picture.getArticleId());
                if(null != article){
                    picture.setArticleTitle(article.getTitle());
                }
            }else {
                picture.setArticleId(null);
                picture.setArticleTitle(null);
            }

            pictureService.updateById(picture);   // 更新
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 修改状态（后台-->主页-->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/updateHomeState",method = RequestMethod.POST)
    public String updateHomeState(@Param("id") Integer id, @Param("stateValue") Integer stateValue) {
        try{
            if(stateValue.equals(SHOW_STATE)){
                Picture old = pictureService.selectById(id);

                Wrapper<Picture> wrapper = new EntityWrapper<>();
                Picture picture = pictureService.selectOne(
                        wrapper.eq("type",old.getType()).eq("state",SHOW_STATE).eq("sort",old.getSort()));
                if(picture != null){
                    return "处理失败，该排序下的已经有显示的新闻图片，若修改请先关闭已显示的";
                }
            }

            pictureService.updateStateById(id, stateValue);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 删除（后台-->主页-->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/deleteHome",method = RequestMethod.POST)
    public String deleteHome(@Param("id") Integer id) {
        try{
            String pictureUrl = pictureService.selectById(id).getPictureUrl();
            deleteFile(pictureUrl);
            pictureService.deleteById(id);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

}
