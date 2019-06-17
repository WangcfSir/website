package com.website.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.website.system.model.ArticleEasy;
import com.website.system.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  （文件公告、行业动态、政策法规）服务类
 *
 * @author yanqb
 * @since 2018-11-18
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取图片文章（供后台配置主页图片链接使用）
     */
    List<ArticleEasy> getArticleEasyForPicture();

    /**
     * 获取文章前四条（根据类型，供网站主页动态显示最新信息）
     */
    List<Article> getRankingByType(Integer type, Integer rowNumber);

    /**
     * 修改文章状态(显示不显示)
     */
    void updateStateById(@Param("id")Integer id, @Param("stateValue")Integer stateValue);
}
