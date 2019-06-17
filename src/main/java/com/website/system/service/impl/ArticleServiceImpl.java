package com.website.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.website.system.dao.ArticleMapper;
import com.website.system.model.ArticleEasy;
import com.website.system.model.Article;
import com.website.system.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * （文件公告、行业动态、政策法规）实现类
 *
 * @author yanqb
 * @since 2018-11-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    ArticleMapper mapper;

    @Override
    public List<ArticleEasy> getArticleEasyForPicture() {
        return mapper.getArticleEasyForPicture();
    }

    @Override
    public List<Article> getRankingByType(Integer type, Integer rowNumber) {
        return mapper.getRankingByType(type, rowNumber);
    }

    @Override
    public void updateStateById(Integer id, Integer stateValue){
        mapper.updateStateById(id, stateValue);
    }

}
