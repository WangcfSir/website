package com.website.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.website.system.model.Article;
import com.website.system.model.Company;
import com.website.system.model.Download;
import com.website.system.model.Picture;
import com.website.system.model.vo.ArticleTransform;
import com.website.system.model.vo.ArticleTypeEnum;
import com.website.system.service.ArticleService;
import com.website.system.service.AssociationService;
import com.website.system.service.DownloadService;
import com.website.system.service.PictureService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * website项目网页
 * （A B C D类型一致，所以归为一类；分别代表为：文件公告、行业动态、政策法规
 *      协会概况 为一条内容情况；下载中心 最后处理；）
 *
 * @author yanqb
 * @date 2018-11-16 15:43
 */
@Controller
@RequestMapping("/page/")
public class PageController {

    private static final Integer PAGE_SIZE = 10;
    private static final Integer FOUR_ROW = 4;
    private static final Integer SIX_ROW = 6;
    private Company company = new Company("郑州市桐柏南路220号河南省安装公司院内南楼204室","0371-67183507","176652698@qq.com","450000");

    @Resource
    private ArticleService articleService;

    @Resource
    private PictureService pictureService;

    @Resource
    private AssociationService associationService;

    @Resource
    private DownloadService downloadService;

    @RequestMapping(value = {"","/index"})
    public String pageIndex(Model model) {
//        Wrapper<Picture> rollPictureWrapper = new EntityWrapper<Picture>().eq("type", 2).and().eq("state", 1);
//        model.addAttribute("rollPicture", new JSONArray(Collections.singletonList(pictureService.selectList(rollPictureWrapper))));
        Wrapper<Picture> newsPictureWrapper = new EntityWrapper<Picture>().eq("type", 1).and().eq("state", 1).orderBy("upload_time", false);
        model.addAttribute("newsPicture", new JSONArray(Collections.singletonList(pictureService.selectList(newsPictureWrapper))));

        model.addAttribute("oneArticle", new JSONArray(Collections.singletonList(articleService.getRankingByType(1,SIX_ROW))));
        model.addAttribute("twoArticle", new JSONArray(Collections.singletonList(articleService.getRankingByType(2,SIX_ROW))));
        model.addAttribute("threeArticle", new JSONArray(Collections.singletonList(articleService.getRankingByType(3,SIX_ROW))));
        model.addAttribute("otherArticle", new JSONArray(Collections.singletonList(articleService.getRankingByType(0,SIX_ROW))));
        model.addAttribute("downloadRank", new JSONArray(Collections.singletonList(downloadService.getRankingFour(FOUR_ROW))));
        return "page/index";
    }

    @RequestMapping("summary{type}")
    public String pageSummary(Model model, @PathVariable String type) {

        type = type.equals("")?"1":type;
        model.addAttribute("association",associationService.selectById(type));
        model.addAttribute("company", company);
        model.addAttribute("articleArray", getRanking());
        return "page/summary";
    }

    @RequestMapping("articleList{type}")
    public String pageArticleList(Model model, @PathVariable String type) {
        Page<Article> pageInfo = new Page<>(1,PAGE_SIZE);
        Wrapper<Article> wrapper = new EntityWrapper<Article>().eq("type", type).and().eq("state", 1).orderBy("create_time", false);
        List<Article> articleList = articleService.selectPage(pageInfo, wrapper).getRecords();
        model.addAttribute("articleList", new JSONArray(Collections.singletonList(articleList)));

        // 计算总页数
        int totalPageNum = (articleService.selectCount(wrapper) + PAGE_SIZE - 1) / PAGE_SIZE;
        model.addAttribute("totalPageNum", totalPageNum);

        model.addAttribute("typeName", ArticleTypeEnum.getName(Integer.parseInt(type)));
        model.addAttribute("type", type);
        model.addAttribute("company", company);
        model.addAttribute("articleArray", getRanking());
        return "page/articleList";
    }

    @ResponseBody
    @RequestMapping("articleListPage")
    public JSONArray pageArticleListPage(@RequestParam Integer type, @RequestParam Integer current) {
        Page<Article> pageInfo = new Page<>(current,PAGE_SIZE);

        Wrapper<Article> wrapper = new EntityWrapper<Article>().eq("type", type).and().eq("state", 1).orderBy("create_time", false);
        List<Article> articleList = articleService.selectPage(pageInfo, wrapper).getRecords();

        JSONArray array = new JSONArray(Collections.singletonList(articleList));
        System.out.println(array);
        return array;
    }

    @RequestMapping("article/{id}")
    public String pageArticleById(Model model, @PathVariable String id) {
        Article article = articleService.selectById(id);
        model.addAttribute("article", ArticleTransform.transform(article));
        model.addAttribute("company", company);
        model.addAttribute("articleArray", getRanking());
        return "page/article";
    }

    @RequestMapping("download")
    public String pageDownload(Model model) {
        Wrapper<Download> downloadWrapper = new EntityWrapper<Download>().eq("state", 1).orderBy("upload_time", false);
        model.addAttribute("files", new JSONArray(Collections.singletonList(downloadService.selectList(downloadWrapper))));
        model.addAttribute("company", company);
        model.addAttribute("articleArray", getRanking());
        return "page/download";
    }

    @RequestMapping("search")
    public String pageSearch(Model model, @Param("content") String content) {

        model.addAttribute("articleList",null);
        if(content != null && !content.equals("")){
            Wrapper<Article> wrapper = new EntityWrapper<Article>().eq("state", 1).and()
                    .like("title", content.trim()).orderBy("create_time", false);
            List<Article> articleList = articleService.selectList(wrapper);
            model.addAttribute("articleList", new JSONArray(Collections.singletonList(articleList)));
        }
        model.addAttribute("company", company);
        model.addAttribute("content", content);
        model.addAttribute("articleArray", getRanking());
        return "page/search";
    }

    /**
     * 最新动态（网站左侧）
     */
    private JSONArray getRanking(){
        List<Article> articleFiveList = articleService.getRankingByType(null,5);
        for(Article article:articleFiveList){
            String title = article.getTitle();
            if(title.length()>15){
                article.setTitle(title.substring(0,15) + "....");
            }
        }
        return new JSONArray(Collections.singletonList(articleFiveList));
    }

}
