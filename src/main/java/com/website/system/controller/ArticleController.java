package com.website.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.website.common.controller.BaseController;
import com.website.common.warpper.ResultMap;
import com.website.system.model.Article;
import com.website.system.model.vo.ArticleTransform;
import com.website.system.model.vo.ArticleVO;
import com.website.system.service.ArticleService;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("manage/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;

    /**
     * 跳转到 文章中心（文件公告、行业动态、政策法规）
     */
    @RequestMapping("/{type}")
    public String article(Model model, @PathVariable String type) {
        model.addAttribute("type",type);
        return "manage/article";
    }

    /**
     * 加载数据 文章中心
     *
     */
    @ResponseBody
    @RequestMapping("/articleTable/{type}")

    public ResultMap articleTable(@PathVariable String type, @RequestParam(required =false) String title,
                                  @RequestParam Integer page, @RequestParam Integer limit) {
        ResultMap<List<ArticleVO>> resultMap = new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, null);
        Page<Article> pageInfo = new Page<>(page,limit);
        Wrapper<Article> wrapper = new EntityWrapper<>();
        wrapper =  wrapper.eq("type", type);
        if(title != null && !title.equals("")){
            wrapper =  wrapper.eq("type", type).and().like("title", title);
        }

        List<Article> articleList = articleService.selectPage(pageInfo, wrapper.orderBy("create_time", false)).getRecords();
        resultMap.setCount(articleService.selectCount(wrapper));
        resultMap.setData(ArticleTransform.transform(articleList));
        return resultMap;
    }

    /**
     * 发布页面 文章
     */
    @RequestMapping("/toArticleAdd")
    public String toArticleAdd() {
        return "manage/articleAdd";
    }

    /**
     * 保存发布 文章
     */
    @ResponseBody
    @RequestMapping(value = "/articleAdd",method = RequestMethod.POST)
    public String articleAdd(@ModelAttribute Article article) {
        try {
            article.setState(SHOW_STATE);
            article.setCreateUser(getCurrentUser().getId());
            article.setCreateTime(new Date());
            articleService.insert(article);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 查看页面（文章-->列表操作） replaceAll用于解决input标签无法显示""问题
     */
    @RequestMapping("/toArticleRead/{id}")
    public String articleRead(Model model, @PathVariable Integer id) {
        Article article = articleService.selectById(id);
        article.setTitle(article.getTitle().replaceAll("\"","&quot;"));
        model.addAttribute("article", ArticleTransform.transform(article));
        return "manage/articleRead";
    }

    /**
     * 修改页面（文章-->列表操作）
     */
    @RequestMapping("/toArticleUpdate/{id}")
    public String getArticle(Model model, @PathVariable Integer id) {
        Article article = articleService.selectById(id);
        article.setTitle(article.getTitle().replaceAll("\"","&quot;"));
        model.addAttribute("article", article);
        return "manage/articleUpdate";
    }

    /**
     * 保存修改 文章
     */
    @ResponseBody
    @RequestMapping("/articleUpdate")
    public String articleUpdate(@ModelAttribute Article article) {
        try{
            article.setModifyUser(getCurrentUser().getId());
            article.setModifyTime(new Date());
            articleService.updateById(article);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 修改状态（文章-->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/updateArticleState",method = RequestMethod.POST)
    public String updateArticleState(@Param("id") Integer id, @Param("stateValue") Integer stateValue) {
        try{
            articleService.updateStateById(id, stateValue);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

}
