package com.website.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.website.system.model.Article;
import com.website.system.model.ArticleEasy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  文章新闻 dao接口
 *
 * @author yanqb
 * @since 2018-11-18
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleEasy> getArticleEasyForPicture();

    List<Article> getRankingByType(@Param("type") Integer type, @Param("rowNumber") Integer rowNumber);

    void updateStateById(@Param("id")Integer id, @Param("stateValue")Integer stateValue);

}