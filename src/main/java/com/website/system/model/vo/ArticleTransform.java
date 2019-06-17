package com.website.system.model.vo;

import com.website.common.factory.ConstantFactory;
import com.website.core.util.DateUtil;
import com.website.system.model.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleTransform {
    public static ArticleVO transform(Article article){
        ArticleVO articleVO = new ArticleVO();
        articleVO.setId(article.getId());
        articleVO.setType(article.getType());
        articleVO.setTypeName(ArticleTypeEnum.getName(article.getType()));
        articleVO.setCreateUser(ConstantFactory.me().getUserNameById(article.getCreateUser()));
        articleVO.setCreateTime(DateUtil.formatDate(article.getCreateTime(),""));
        articleVO.setTitle(article.getTitle());
        articleVO.setContent(article.getContent());
        articleVO.setState(article.getState());
        return articleVO;
    }

    public static List<ArticleVO> transform(List<Article> articleList){
        if (articleList == null || articleList.isEmpty()){
            return Collections.emptyList();
        }
        List<ArticleVO> cds = new ArrayList<>();
        articleList.forEach(article -> cds.add(transform(article)));
        return cds;
    }
}
